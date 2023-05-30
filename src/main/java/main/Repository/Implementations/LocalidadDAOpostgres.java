package main.Repository.Implementations;

import main.Model.localidad;
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
public class LocalidadDAOpostgres implements IDao<localidad> {
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
    private static final String select_with_id = "SELECT * FROM mydb.localidad WHERE k_Localidad = ?;";
    private static final String insert = "INSERT INTO mydb.localidad VALUES(?,?,?);";
    private static final String delete = "DELETE FROM mydb.localidad WHERE k_Localidad = ?;";
    private static final String update = "UPDATE mydb.localidad SET n_Localidad = ?, n_Localidad = ?,"
            +"WHERE k_numidentificacion = ?;";

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
                int localidad = resultados.getInt(1);
                String nombre = resultados.getString(2);
                int ciudad_k_ciudad = resultados.getInt(3); //verificar nombre Modelo
                Localidad localidad = new Localidad(localidad, nombre, ciudad_k_ciudad);
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
    public localidad listar(int id) throws SQLException {
        ResultSet resultados = null;
        localidad localidad = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int numIdentificacion = resultados.getInt(1);
                String tipoIdentificacion = resultados.getString(2);
                java.sql.Timestamp fechaNacimiento = resultados.getTimestamp(3);
                String nacionalidad = resultados.getString(4);
                int numCelular = resultados.getInt(5);
                char sexo = resultados.getString(6).charAt(0);
                String eps = resultados.getString(7);
                String primerNombre = resultados.getString(8);
                String segundoNombre = resultados.getString(9);
                String primerApellido = resultados.getString(10);
                String segundoApellido = resultados.getString(11);
                int cuenta_k_cuenta = resultados.getInt(12);
                localidad = new localidad(numIdentificacion, numCelular, cuenta_k_cuenta, tipoIdentificacion,
                        nacionalidad,
                        eps, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, sexo);
                logger.info("Se trajo el localidad con identificacion: " + numIdentificacion + ": " + localidad);
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
    public localidad agregar(localidad localidad) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, localidad.getIdentificacion());
            consulta.setTimestamp(2, localidad.getFechaNacimiento());
            consulta.setString(3, localidad.getNacionalidad());
            consulta.setLong(4, localidad.getNumCelular());
            consulta.setString(5, String.valueOf(localidad.getSexo()));
            consulta.setString(6, localidad.getEps());
            consulta.setString(7, localidad.getPrimerNombre());
            consulta.setString(7, localidad.getSegundoNombre());
            consulta.setString(7, localidad.getPrimerApellido());
            consulta.setString(7, localidad.getSegundoApellido());
            consulta.setInt(7, localidad.getCuenta_k_cuenta());
            consulta.execute();
            logger.info("Se guardo el localidad:" + localidad.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar el localidad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return localidad;
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó el localidad con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar el localidad, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public localidad actualizar(localidad localidad) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setInt(12, localidad.getIdentificacion());
            consulta.setString(1, localidad.getTipoIdentificacion());
            consulta.setTimestamp(2, localidad.getFechaNacimiento());
            consulta.setString(3, localidad.getNacionalidad());
            consulta.setLong(4, localidad.getNumCelular());
            consulta.setString(5, String.valueOf(localidad.getSexo()));
            consulta.setString(6, localidad.getEps());
            consulta.setString(7, localidad.getPrimerNombre());
            consulta.setString(8, localidad.getSegundoNombre());
            consulta.setString(9, localidad.getPrimerApellido());
            consulta.setString(10, localidad.getSegundoApellido());
            consulta.setInt(11, localidad.getCuenta_k_cuenta());
            consulta.executeUpdate();
            logger.info("se actualizó el localidad " + localidad.getIdentificacion() + " a " + localidad.toString());
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la localidad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return localidad;
    }

}
