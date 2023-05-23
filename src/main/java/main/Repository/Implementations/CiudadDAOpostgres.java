package main.Repository.Implementations;

import main.Model.Ciudad;
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

@Service
public class CiudadDAOpostgres implements IDao<Ciudad> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(CiudadDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Ciudad ("
            + "k_Ciudad INT NOT NULL,"
            + "n_Ciudad VARCHAR(40) NOT NULL,"
            + "f_InicioServicio TIMESTAMP NULL,"
            + "f_FinalServicio TIMESTAMP NOT NULL,"
            + "PRIMARY KEY (k_Ciudad));";
    private static final String select = "SELECT * FROM mydb.ciudad;";
    private static final String select_with_id = "SELECT * FROM mydb.ciudad WHERE k_ciudad = ?;";
    private static final String insert = "INSERT INTO mydb.ciudad VALUES(?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.ciudad WHERE k_ciudad = ?;";
    private static final String update = "UPDATE mydb.ciudad SET n_ciudad = ?, f_inicioservicio = ?, f_finalservicio = ? WHERE k_ciudad = ?;";

    @Override
    public void CrearTabla() throws SQLException { // FUNCIONAl
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
    public List<Ciudad> listarTodos() throws SQLException { // FUNCIONAL
        Statement consulta = null;
        ResultSet resultados = null;
        List<Ciudad> ListaCiudades = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int IDCiudad = resultados.getInt(1);
                String Nombre = resultados.getString(2);
                java.sql.Timestamp InicioServicio = resultados.getTimestamp(3);
                java.sql.Timestamp FinServicio = resultados.getTimestamp(4);
                Ciudad ciudad = new Ciudad(IDCiudad, Nombre, InicioServicio, FinServicio);
                logger.info("Se trajo una ciudad: " + ciudad);
                ListaCiudades.add(ciudad);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar odontologos, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaCiudades;
    }

    @Override
    public Ciudad listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        Ciudad ciudad = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int IDCiudad = resultados.getInt(1);
                String Nombre = resultados.getString(2);
                java.sql.Timestamp inicioServicio = resultados.getTimestamp(3);
                java.sql.Timestamp finalServicio = resultados.getTimestamp(4);
                ciudad = new Ciudad(IDCiudad, Nombre, inicioServicio, finalServicio);
                logger.info("Se trajo la ciudad con id: " + IDCiudad + ": " + ciudad);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer la ciudad con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ciudad;
    }

    @Override
    public void agregar(Ciudad ciudad) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, ciudad.getIDCiudad());
            consulta.setString(2, ciudad.getNombre());
            consulta.setTimestamp(3, ciudad.getInicioServicio());
            consulta.setTimestamp(4, ciudad.getFinalServicio());
            consulta.execute();
            logger.info("Se guardo la ciudad:" + ciudad.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar la ciudad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {// FUNCIONAL

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó la ciudad con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar la ciudad, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Ciudad actualizar(Ciudad ciudad) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setString(1, ciudad.getNombre());
            consulta.setTimestamp(2, ciudad.getInicioServicio());
            consulta.setTimestamp(3, ciudad.getFinalServicio());
            consulta.setInt(4, ciudad.getIDCiudad());
            consulta.executeUpdate();
            logger.info("se actualizó la ciudad " + ciudad.getIDCiudad() + " a " + ciudad);
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la ciudad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return ciudad;
    }

}
