package main.Repository.Implementations;

import main.Model.Bicicleta;
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
public class BicicletaDAOpostgres implements IDao<Bicicleta> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(CiudadDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Bicicleta("
            + "k_Bicicleta INT NOT NULL,"
            + "n_Tipo VARCHAR(40) NOT NULL,"
            + "PRIMARY KEY (k_Bicicleta));";
    private static final String select = "SELECT * FROM mydb.bicicleta;";
    private static final String select_with_id = "SELECT * FROM mydb.bicicleta WHERE k_bibicleta = ?;";
    private static final String insert = "INSERT INTO mydb.ciudad VALUES(?,?);";
    private static final String delete = "DELETE FROM mydb.ciudad WHERE k_bicicleta= ?;";
    private static final String update = "UPDATE mydb.localidad SET n_tipo = ? WHERE k_ciudad = ?;";

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
    public List<Bicicleta> listarTodos() throws SQLException { // FUNCIONAL
        return null;
    }

    @Override
    public Bicicleta listar(int id) throws SQLException { // FUNCIONAL
        return null;
    }

    @Override
    public Bicicleta agregar(Bicicleta bicicleta) throws SQLException { // FUNCIONAL

        return null;
    }

    @Override
    public void eliminar(int id) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó la bicicleta con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar la bicicleta, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Bicicleta actualizar(Bicicleta bicicleta) throws SQLException { // FUNCIONAL
        return null;
    }

}
