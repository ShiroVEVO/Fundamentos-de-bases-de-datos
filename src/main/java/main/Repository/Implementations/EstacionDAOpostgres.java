package main.Repository.Implementations;

import main.Model.Estacion;
import main.Repository.IDao;
import main.Repository.Configuration.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

//@Service
public class EstacionDAOpostgres implements IDao<Estacion> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(EstacionDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Estacion("
            + "k_Estacion INT NOT NULL,"
            + "q_AnclajesDisponibles INT NOT NULL,"
            + "q_AnclajesTotales INT NOT NULL,"
            + "n_Direccion VARCHAR(60) NOT NULL,"
            + "Localidad_k_Localidad INT NOT NULL,"
            + "PRIMARY KEY (k_Estacion),"
            + "CONSTRAINT fk_Estacion_Localidad1"
            + "FOREIGN KEY (Localidad_k_Localidad)"
            + "REFERENCES mydb.Localidad (k_Localidad)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_Estacion_Localidad1_idx"
            + "ON mydb.Estacion (Localidad_k_Localidad);";
    private static final String select = "SELECT * FROM mydb.estacion;";
    private static final String select_with_id = "SELECT * FROM mydb.estacion WHERE k_estacion = ?;";
    private static final String insert = "INSERT INTO mydb.estacion VALUES(?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.estacion WHERE k_estacion= ?;";
    private static final String update = "UPDATE mydb.estacion SET q_anclajesdisponibles = ?, q_anclajestotales = ?,"
    + "n_direccion = ?, localidad_k_localidad = ? WHERE k_estacion = ?;";
    
    @Override
    public void CrearTabla() throws SQLException {
        conexion.conectar();
        Statement consulta = null;
        try {
            consulta = conexion.conn.createStatement();
            consulta.execute(createTable);
            logger.info("Se creó la tabla");
        } catch (Exception e) {
            logger.info("No se creó la tabla: " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
    }

    @Override
    public List<Estacion> listarTodos() throws SQLException { // FUNCIONAL
        Statement consulta = null;
        ResultSet resultados = null;
        List<Estacion> ListaEstaciones = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int k_estacion = resultados.getInt(1);
                int anclajesDisponibles = resultados.getInt(2);
                int anclajesTotales = resultados.getInt(3);
                String direccion = resultados.getString(4);
                int localidad_k_localidad = resultados.getInt(5);

                Estacion estacion = new Estacion(k_estacion, anclajesDisponibles, anclajesTotales, localidad_k_localidad,direccion );
                logger.info("Se trajo una estacion: " + estacion);
                ListaEstaciones.add(estacion);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar las estaciones, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaEstaciones;
    }

    @Override
    public Estacion listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        Estacion estacion = null;

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_estacion = resultados.getInt(1);
                int anclajesDisponibles = resultados.getInt(2);
                int anclajesTotales = resultados.getInt(3);
                String direccion = resultados.getString(4);
                int localidad_k_localidad = resultados.getInt(5);

                estacion = new Estacion(k_estacion, anclajesDisponibles, anclajesTotales, localidad_k_localidad,direccion);
                logger.info("Se trajo la estacion con id: " + k_estacion + ": " + estacion);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer la estacion con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return estacion;
    }

    @Override
    public Estacion agregar(Estacion estacion) throws SQLException { // FUNCIONAL
        try {   //Consultar Nombres modelo
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);

            consulta.setInt(1, estacion.getK_estacion());
            consulta.setInt(2, estacion.getAnclajesDisponibles());
            consulta.setInt(3, estacion.getAnclajesTotales());
            consulta.setInt(4, estacion.getLocalidad_k_localidad());
            consulta.setString(5, estacion.getDireccion());

            consulta.execute();
            logger.info("Se guardo la estacion:" + estacion.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar la estacion, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return estacion;
    }

    @Override
    public void eliminar(int id) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó la estacion con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar la estacion, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Estacion actualizar(Estacion estacion) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);

            consulta.setInt(5, estacion.getK_estacion());
            consulta.setInt(1, estacion.getAnclajesDisponibles());
            consulta.setInt(2, estacion.getAnclajesTotales());
            consulta.setString(3, estacion.getDireccion());
            consulta.setInt(4, estacion.getLocalidad_k_localidad());

            consulta.executeUpdate();
            logger.info("se actualizó la estacion " + estacion.getK_estacion() + " a " + estacion);
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la estacion, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return estacion;

    }
}
