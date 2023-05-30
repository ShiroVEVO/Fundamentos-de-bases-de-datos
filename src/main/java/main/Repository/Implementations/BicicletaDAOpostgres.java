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
    private static final Logger logger = Logger.getLogger(BicicletaDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Bicicleta("
            + "k_Bicicleta INT NOT NULL,"
            + "n_Tipo VARCHAR(40) NOT NULL,"
            + "PRIMARY KEY (k_Bicicleta));";
    private static final String select = "SELECT * FROM mydb.bicicleta;";
    private static final String select_with_id = "SELECT * FROM mydb.bicicleta WHERE k_bibicleta = ?;";
    private static final String insert = "INSERT INTO mydb.bicicleta VALUES(?,?);";
    private static final String delete = "DELETE FROM mydb.bicicleta WHERE k_bicicleta= ?;";
    private static final String update = "UPDATE mydb.bicicleta SET n_tipo = ? WHERE k_bicicleta = ?;";

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
        Statement consulta = null;
        ResultSet resultados = null;
        List<Bicicleta> ListaBicicletas = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int k_bibicleta = resultados.getInt(1);
                String tipo = resultados.getString(2);
                Bicicleta bicicleta = new Bicicleta(k_bibicleta, tipo);
                logger.info("Se trajo una bibicleta: " + bicicleta);
                ListaBicicletas.add(bicicleta);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar bicicletas, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaBicicletas;
    }

    @Override
    public Bicicleta listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        Bicicleta bicicleta = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_bibicleta = resultados.getInt(1);
                String tipo = resultados.getString(2);
                bicicleta = new Bicicleta(k_bibicleta, tipo);
                logger.info("Se trajo la bicicleta con id: " + k_bibicleta + ": " + bicicleta);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer la ciudad con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return bicicleta;
    }

    @Override
    public Bicicleta agregar(Bicicleta bicicleta) throws SQLException { // FUNCIONAL
        try {   //Consultar Nombres modelo
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, bicicleta.getK_bibicleta());
            consulta.setString(2, bicicleta.getTipo());
            consulta.execute();
            logger.info("Se guardo la bicicleta:" + bicicleta.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar la ciudad, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return bicicleta;
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
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setString(1, bicicleta.getTipo());
            consulta.setInt(2, bicicleta.getK_bibicleta());
            consulta.executeUpdate();
            logger.info("se actualizó la bicicleta " + bicicleta.getK_bibicleta() + " a " + bicicleta);
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la bicileta, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return bicicleta;
    }

}
