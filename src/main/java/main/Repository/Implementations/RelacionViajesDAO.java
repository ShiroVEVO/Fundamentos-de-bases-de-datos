package main.Repository.Implementations;

import main.Model.Bicicleta;
import main.Model.Estacion;
import main.Model.RelacionVEB;
import main.Repository.IDao;
import main.Repository.Configuration.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RelacionViajesDAO {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(RelacionViajesDAO.class.getName());

    private static final String estacionYBicicleta = "SELECT * FROM mydb.bicicleta, mydb.estaciotienebicicleta, mydb.estacion WHERE mydb.bicicleta.k_bicicleta = mydb.estaciotienebicicleta.fk_bicicleta AND mydb.estaciotienebicicleta.fk_estacion = mydb.estacion.k_estacion;";
    private static final String bicicletaEnEstacion = "SELECT * FROM mydb.estaciotienebicicleta WHERE fk_estacion = ?;";
    private static final String deleteBicicletaEstacion = "DELETE from mydb.estaciotienebicicleta WHERE mydb.estaciotienebicicleta.fk_estacion = ? AND mydb.estaciotienebicicleta.fk_bicicleta = ?;";
    private static final String insertBicicletaEstacion = "INSERT INTO mydb.estaciotienebicicleta VALUES(?,?);";
    private static final String creaVinculaViaje = "INSERT INTO mydb.estaciotienebicicleta_has_viaje VALUES(?, ?, ?, ?);";
    private static final String selectVinculoViaje = "SELECT * FROM mydb.estaciotienebicicleta_has_viaje where viaje_k_viaje = ?;";

    public List<Estacion> obtenerEstacionesConBicicletas() throws SQLException {
        Statement consulta = null;
        ResultSet resultados = null;
        List<Estacion> ListaEstaciones = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(estacionYBicicleta);

            int k_estacion_ant = 0;
            while (resultados.next()) {
                int k_estacion = resultados.getInt(5);
                if (k_estacion == k_estacion_ant) {
                    continue;
                }
                k_estacion_ant = k_estacion;
                int ancl_disponibles = resultados.getInt(6);
                int ancl_totales = resultados.getInt(7);
                String direcion = resultados.getString(8);
                int fk_localidad = resultados.getInt(9);
                Estacion estacion = new Estacion(k_estacion, ancl_disponibles, ancl_totales, fk_localidad, direcion);
                logger.info("Se trajo una estacion: " + estacion);
                ListaEstaciones.add(estacion);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar bicicletas, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaEstaciones;
    }

    public int obtenerIdBicicletaDeEstacion(int idEstacion) throws SQLException {
        ResultSet resultados = null;
        int k_bicicleta = -1;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(bicicletaEnEstacion);
            consulta.setInt(1, idEstacion);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                k_bicicleta = resultados.getInt(2);

                logger.info("Se trajo la bicicleta" + k_bicicleta + " de la estacion: " + idEstacion);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer una bicicleta de la estacion->id: " + idEstacion + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return k_bicicleta;
    }

    public boolean vincularBicicletaEstacion(int idEstacion, int idBicicleta) throws SQLException {
        boolean exito = false;

        try {   //Consultar Nombres modelo
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insertBicicletaEstacion);
            consulta.setInt(1, idEstacion);
            consulta.setInt(2, idBicicleta);
            consulta.execute();
            exito = true;
            logger.info("Se logró vincular la bicicleta: " + idBicicleta + " con la estacion: "+idEstacion);
        } catch (Exception e) {
            logger.warning("No se pudo vincular la bicicleta, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return exito;
    }

    public boolean desvincularBicletaEstacion(int idEstacion, int idBicicleta) throws SQLException {
        boolean exito = false;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(deleteBicicletaEstacion);
            consulta.setInt(1, idEstacion);
            consulta.setInt(2, idBicicleta);

            exito = consulta.executeUpdate() != 0; // Si se afectó por lo menos un registro
            // Nota: En ningun momento deberia poder dar más de uno porque se controla que
            // una bicicleta no esté
            // en varias estaciones a la vez
            logger.info("Eliminada relacion entre la bicicleta" + idBicicleta + " y la estacion: " + idEstacion);
        } catch (Exception e) {
            logger.info("Se presento un error al traer una bicicleta de la estacion->id: " + idEstacion + " ," + e);
        } finally {
            conexion.desconectar();
        }
        return exito;
    }

    public RelacionVEB obtenerVinculoViaje(int idViaje) throws SQLException {
        ResultSet resultados = null;
        RelacionVEB relacion = null;

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(selectVinculoViaje);
            consulta.setInt(1, idViaje);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_estacion = resultados.getInt(1);
                int k_bicicleta = resultados.getInt(2);
                boolean is_entrega = resultados.getBoolean(4);

                relacion = new RelacionVEB(k_estacion, k_bicicleta, idViaje, is_entrega);
                logger.info("Se trajo la relacion VEB con ids: ("+k_estacion+","+k_bicicleta+","+ idViaje+")");
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer el vinculo del viaje con id: " + idViaje + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return relacion;
    }

    public boolean vincularViaje(int idEstacion, int idBicicleta, int idViaje, boolean entrega) throws SQLException {
        boolean exito = false;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(creaVinculaViaje);
            consulta.setInt(1, idEstacion);
            consulta.setInt(2, idBicicleta);
            consulta.setInt(3, idViaje);
            consulta.setBoolean(4, entrega);
            consulta.execute();
            exito = true;
            logger.info("Vinculacion de viaje exitosa E:" + idEstacion + " B:" + idEstacion + " V:");
        } catch (Exception e) {
            logger.info("Error al vincular viaje E:" + idEstacion + " B:" + idEstacion + " V:"+ idViaje+" ," + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return exito;
    }

}
