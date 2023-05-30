package main.Repository.Implementations;

import main.Model.FormaPago;
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
public class FormaPagoDAOpostgres implements IDao<FormaPago> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(FormaPagoDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.FormaPago("
            + "k_NumeroTarjeta INT NOT NULL,"
            + "n_NombreTarjeta VARCHAR(45) NOT NULL,"
            + "n_Estado VARCHAR(45) NOT NULL,"
            + "f_FechaVencimientoTarjeta VARCHAR(4) NOT NULL,"
            + "Usuario_k_NumIdentificacion INT NOT NULL,"
            + "PRIMARY KEY (k_NumeroTarjeta),"
            + "CONSTRAINT fk_FormaPago_Usuario1"
            + "FOREIGN KEY (Usuario_k_NumIdentificacion)"
            + "REFERENCES mydb.Usuario (k_NumIdentificacion)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_FormaPago_Usuario1_idx"
            + "ON mydb.FormaPago (Usuario_k_NumIdentificacion);";
    private static final String select = "SELECT * FROM mydb.formapago;";
    private static final String select_with_id = "SELECT * FROM mydb.formapago WHERE k_numerotarjeta = ?;";
    private static final String insert = "INSERT INTO mydb.formapago VALUES(?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.formapago WHERE k_numeroTarjeta= ?;";
    private static final String update = "UPDATE mydb.viaje SET n_nombretarjeta = ?, n_estado = ?,"
    + "f_fechavencimientotarjeta = ?, usuario_k_numidentificacion = ? WHERE k_numerotarjeta = ?;";
    
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
    public List<FormaPago> listarTodos() throws SQLException { // FUNCIONAL
        Statement consulta = null;
        ResultSet resultados = null;
        List<FormaPago> ListaFormaPagos = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int k_numerotarjeta = resultados.getInt(1);
                int usuario_k_numidentificacion = resultados.getInt(2);
                String nombreTarjeta = resultados.getString(3);
                String estado = resultados.getString(4);
                String fechaVencimientoTarjeta = resultados.getString(5);

                FormaPago formapago = new FormaPago(k_numerotarjeta, usuario_k_numidentificacion, nombreTarjeta, estado,fechaVencimientoTarjeta );
                logger.info("Se trajo una forma de pago: " + formapago);
                ListaFormaPagos.add(formapago);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar las formas de pago, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaFormaPagos;
    }

    @Override
    public FormaPago listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        FormaPago formapago = null;

        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_numerotarjeta = resultados.getInt(1);
                int usuario_k_numidentificacion = resultados.getInt(2);
                String nombreTarjeta = resultados.getString(3);
                String estado = resultados.getString(4);
                String fechaVencimientoTarjeta = resultados.getString(5);

                formapago = new FormaPago(k_numerotarjeta, usuario_k_numidentificacion, nombreTarjeta, estado,fechaVencimientoTarjeta );
                logger.info("Se trajo la forma de pago con id: " + k_numerotarjeta + ": " + formapago);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer la forma de pago con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return formapago;
    }

    @Override
    public FormaPago agregar(FormaPago formapago) throws SQLException { // FUNCIONAL
        try {   //Consultar Nombres modelo
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);

            consulta.setInt(1, formapago.getK_numerotarjeta());
            consulta.setInt(2, formapago.getUsuario_k_numidentificacion());
            consulta.setString(3, formapago.getNombreTarjeta());
            consulta.setString(4, formapago.getEstado());
            consulta.setString(5, formapago.getFechaVencimientoTarjeta());
            consulta.execute();
            logger.info("Se guardo la forma de pago:" + formapago.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar la forma de pago, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return formapago;
    }

    @Override
    public void eliminar(int id) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó la forma pago con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar la forma de pago, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public FormaPago actualizar(FormaPago formapago) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);

            consulta.setInt(5, formapago.getK_numerotarjeta());
            consulta.setInt(1, formapago.getUsuario_k_numidentificacion());
            consulta.setString(2, formapago.getNombreTarjeta());
            consulta.setString(3, formapago.getEstado());
            consulta.setString(4, formapago.getFechaVencimientoTarjeta());

            consulta.executeUpdate();
            logger.info("se actualizó la forma pago " + formapago.getK_numerotarjeta() + " a " + formapago);
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la forma de pago, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return formapago;

    }
}
