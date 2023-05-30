package main.Repository.Implementations;

import main.Model.Localidad;
import main.Repository.IDao;
import main.Repository.Configuration.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@Service
public class LocalidadDAOpostgres implements IDao<Localidad> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(LocalidadDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Localidad ("
            + "k_Localidad INT NOT NULL"
            + "n_Localidad VARCHAR(40) NOT NULL,"
            + "Ciudad_k_Ciudad INT NOT NULL,"
            + "PRIMARY KEY (k_Localidad),"
            + "CONSTRAINT fk_Localidad_Ciudad"
            + "FOREIGN KEY (Ciudad_k_Ciudad)"
            + "REFERENCES mydb.Ciudad (k_Ciudad)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_Localidad_Ciudad_idx"
            + "ON mydb.localidad (Cuenta_k_Cuenta);";
    private static final String select = "SELECT * FROM mydb.localidad;";
    private static final String select_with_id = "SELECT * FROM mydb.localidad WHERE k_localidad = ?;";
    private static final String insert = "INSERT INTO mydb.localidad VALUES(?,?,?);";
    private static final String delete = "DELETE FROM mydb.localidad WHERE k_localidad = ?;";
    private static final String update = "UPDATE mydb.localidad SET n_localidad = ?, ciudad_k_ciudad = ?,"
            +"WHERE k_localidad = ?;";

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
    public List<Localidad> listarTodos() throws SQLException {
        Statement consulta = null;
        ResultSet resultados = null;
        List<Localidad> Listalocalidades = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);
            while (resultados.next()) {
                int k_localidad = resultados.getInt(1);
                int ciudad_k_ciudad = resultados.getInt(2);
                String n_localidad = resultados.getString(3);
                Localidad localidad = new Localidad(k_localidad, ciudad_k_ciudad, n_localidad);
                logger.info("Se trajo una localidad: " + localidad);
                Listalocalidades.add(localidad);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar localidads, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return Listalocalidades;
    }

    @Override
    public Localidad listar(int id) throws SQLException {
        ResultSet resultados = null;
        Localidad localidad = null;

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_localidad = resultados.getInt(1);
                int ciudad_k_ciudad = resultados.getInt(2);
                String n_localidad = resultados.getString(3);

                localidad = new Localidad(k_localidad, ciudad_k_ciudad,n_localidad);
                logger.info("Se trajo el localidad con identificacion: " +k_localidad  + ": " + localidad);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer el localidad con identificacion: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return localidad;
    }

    @Override
    public Localidad agregar(Localidad Localidad) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, Localidad.getK_localidad());
            consulta.setInt(2, Localidad.getCiudad_k_ciudad());
            consulta.setString(3, Localidad.getN_localidad());
            consulta.execute();
            logger.info("Se guardo el localidad:" + Localidad.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar el localidad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return Localidad;
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó el Localidad con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar el Localidad, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Localidad actualizar(Localidad Localidad) throws SQLException {
        
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setInt(3, Localidad.getK_localidad());
            consulta.setInt(1, Localidad.getCiudad_k_ciudad());
            consulta.setString(3, Localidad.getN_localidad());

            consulta.executeUpdate();
            logger.info("se actualizó el localidad " + Localidad.getK_localidad() + " a " + Localidad.toString());
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la localidad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return Localidad;
    }

}
