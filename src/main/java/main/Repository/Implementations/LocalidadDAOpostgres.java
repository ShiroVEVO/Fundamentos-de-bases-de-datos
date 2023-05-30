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
            + "ON mydb.Localidad (Cuenta_k_Cuenta);";
    private static final String select = "SELECT * FROM mydb.Localidad;";
    private static final String select_with_id = "SELECT * FROM mydb.Localidad WHERE k_localidad = ?;";
    private static final String insert = "INSERT INTO mydb.Localidad VALUES(?,?,?);";
    private static final String delete = "DELETE FROM mydb.Localidad WHERE k_localidad = ?;";
    private static final String update = "UPDATE mydb.Localidad SET n_localidad = ?, ciudad_k_ciudad = ?,"
            + "WHERE k_localidad = ?;";

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
        return null;
    }

    @Override
    public Localidad listar(int id) throws SQLException {
        return null;
    }

    // Terminar desde aqui
    @Override
    public Localidad agregar(Localidad Localidad) throws SQLException {

        return null;
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
        return Localidad;
    }

}
