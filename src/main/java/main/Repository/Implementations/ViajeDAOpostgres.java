package main.Repository.Implementations;

import main.Model.Viaje;
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
public class ViajeDAOpostgres implements IDao<Viaje> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(ViajeDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Viaje("
            + "k_Viaje INT NOT NULL,"
            + "f_Entrega TIMESTAMP NOT NULL,"
            + "f_Desbloqueo TIMESTAMP NOT NULL,"
            + "i_Costo FLOAT NULL,"
            + "Cuenta_k_Cuenta INT NOT NULL,"
            + "PRIMARY KEY (k_Viaje),"
            + "CONSTRAINT fk_Viaje_Cuenta1"
            + "FOREIGN KEY (Cuenta_k_Cuenta)"
            + "REFERENCES mydb.Cuenta (k_Cuenta)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_Viaje_Cuenta1_idx"
            + "ON mydb.Viaje (Cuenta_k_Cuenta);";
    private static final String select = "SELECT * FROM mydb.viaje ORDER BY k_viaje;";
    private static final String select_with_id = "SELECT * FROM mydb.viaje WHERE k_viaje = ?;";
    private static final String insert = "INSERT INTO mydb.viaje VALUES(?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.viaje WHERE k_viaje= ?;";
    private static final String update = "UPDATE mydb.viaje SET f_entrega = ?, f_desbloqueo = ?,"
    + "i_costo = ?, cuenta_k_cuenta = ? WHERE k_viaje = ?;";
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
    public List<Viaje> listarTodos() throws SQLException { // FUNCIONAL
        Statement consulta = null;
        ResultSet resultados = null;
        List<Viaje> ListaViajes = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int k_viaje = resultados.getInt(1);
                java.sql.Timestamp f_entrega = resultados.getTimestamp(2);
                java.sql.Timestamp f_desbloqueo = resultados.getTimestamp(3);
                double costo = resultados.getDouble(4);
                int cuenta_k_cuenta = resultados.getInt(5);

                Viaje viaje = new Viaje(k_viaje, cuenta_k_cuenta, f_entrega, f_desbloqueo,costo );
                logger.info("Se trajo un viaje: " + viaje);
                ListaViajes.add(viaje);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar los viajes, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaViajes;
    }

    @Override
    public Viaje listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        Viaje viaje = null;

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_viaje = resultados.getInt(1);
                java.sql.Timestamp f_entrega = resultados.getTimestamp(2);
                java.sql.Timestamp f_desbloqueo = resultados.getTimestamp(3);
                double costo = resultados.getDouble(4);
                int cuenta_k_cuenta = resultados.getInt(5);

                viaje = new Viaje(k_viaje, cuenta_k_cuenta, f_entrega, f_desbloqueo,costo);
                logger.info("Se trajo el viaje con id: " + k_viaje + ": " + viaje);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer el viaje con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return viaje;
    }

    @Override
    public Viaje agregar(Viaje viaje) throws SQLException { // FUNCIONAL
        try {   //Consultar Nombres modelo
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, viaje.getK_viaje());
            consulta.setTimestamp(2, viaje.getF_entrega());
            consulta.setTimestamp(3, viaje.getF_desbloqueo());
            consulta.setDouble(4, viaje.getCosto());
            consulta.setInt(5, viaje.getCuenta_k_cuenta());

            consulta.execute();
            logger.info("Se guardo el viaje:" + viaje.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar el viaje, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return viaje;
    }

    @Override
    public void eliminar(int id) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó el viaje con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar el viaje, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Viaje actualizar(Viaje viaje) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);

            consulta.setInt(5, viaje.getK_viaje());
            consulta.setTimestamp(1, viaje.getF_entrega());
            consulta.setTimestamp(2, viaje.getF_desbloqueo());
            consulta.setDouble(3, viaje.getCosto());
            consulta.setInt(4, viaje.getCuenta_k_cuenta());
            consulta.executeUpdate();
            logger.info("se actualizó el viaje " + viaje.getK_viaje() + " a " + viaje);
        } catch (Exception e) {
            logger.warning("No se pudo actualizar el viaje, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return viaje;
    }

}
