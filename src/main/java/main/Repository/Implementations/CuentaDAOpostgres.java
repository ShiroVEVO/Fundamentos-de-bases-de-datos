package main.Repository.Implementations;

import main.Model.Cuenta;
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
public class CuentaDAOpostgres implements IDao<Cuenta> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(CuentaDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Cuenta ("
            + "k_Cuenta INT NOT NULL,"
            + "i_SaldoFinal FLOAT NULL,"
            + "i_SaldoInicial FLOAT NOT NULL,"
            + "n_estado VARCHAR(10) NOT NULL,"
            + "n_Contraseña VARCHAR(20) NOT NULL,"
            + "n_CorreoElectronico VARCHAR(25) NOT NULL,"
            + "Plan_k_Plan INT NOT NULL,"
            + "PRIMARY KEY (k_Cuenta),"
            + "CONSTRAINT fk_Cuenta_Plan1"
            + "FOREIGN KEY (Plan_k_Plan)"
            + "REFERENCES mydb.Plan (k_Plan)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_Cuenta_Plan1_idx"
            + "ON mydb.Cuenta (Plan_k_Plan);";
    private static final String select = "SELECT * FROM mydb.cuenta;";
    private static final String select_with_id = "SELECT * FROM mydb.cuenta WHERE k_cuenta = ?;";
    private static final String insert = "INSERT INTO mydb.cuenta VALUES (?,?,?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.cuenta WHERE k_cuenta = ?;";
    private static final String update = "UPDATE mydb.cuenta SET i_saldofinal = ?, i_saldoinicial = ?, "
            + "n_estado = ?, n_contraseña = ?, n_correoelectronico = ?, plan_k_plan = ? WHERE k_cuenta = ?;";

    @Override
    public void CrearTabla() throws SQLException { //
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
    public List<Cuenta> listarTodos() throws SQLException { //
        Statement consulta = null;
        ResultSet resultados = null;
        List<Cuenta> ListaCuentas = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int k_cuenta = resultados.getInt(1);
                double saldoFinal = resultados.getDouble(2);
                double saldoInicial = resultados.getDouble(3);
                String estado = resultados.getString(4);
                String contraseña = resultados.getString(5);
                String correoElectronico = resultados.getString(6);
                int plan_k_plan = resultados.getInt(7);
                Cuenta cuenta = new Cuenta(k_cuenta, plan_k_plan, saldoFinal, saldoInicial, estado, contraseña,
                        correoElectronico);
                // logger.info("Se trajo una cuenta: " + cuenta);
                ListaCuentas.add(cuenta);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar las cuentas, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaCuentas;
    }

    @Override
    public Cuenta listar(int id) throws SQLException { //
        ResultSet resultados = null;
        Cuenta cuenta = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int k_cuenta = resultados.getInt(1);
                double saldoFinal = resultados.getDouble(2);
                double saldoInicial = resultados.getDouble(3);
                String estado = resultados.getString(4);
                String contraseña = resultados.getString(5);
                String correoElectronico = resultados.getString(6);
                int plan_k_plan = resultados.getInt(7);
                cuenta = new Cuenta(k_cuenta, plan_k_plan, saldoFinal, saldoInicial, estado, contraseña,
                        correoElectronico);
                // logger.info("Se trajo la cuenta con id: " + id + ": " + cuenta);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer la cuenta con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return cuenta;
    }

    @Override
    public Cuenta agregar(Cuenta cuenta) throws SQLException { //
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, cuenta.getK_cuenta());
            consulta.setDouble(2, cuenta.getSaldoFinal());
            consulta.setDouble(3, cuenta.getSaldoInicial());
            consulta.setString(4, cuenta.getEstado());
            consulta.setString(5, cuenta.getContraseña());
            consulta.setString(6, cuenta.getCorreoElectronico());
            consulta.setInt(7, cuenta.getPlan_k_plan());
            consulta.execute();
            logger.info("Se guardo la cuenta:" + cuenta.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar la cuenta, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return cuenta;
    }

    @Override
    public void eliminar(int id) throws SQLException { //
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó la cuenta con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar la cuenta, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Cuenta actualizar(Cuenta cuenta) throws SQLException { //
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setInt(7, cuenta.getK_cuenta());
            consulta.setDouble(1, cuenta.getSaldoFinal());
            consulta.setDouble(2, cuenta.getSaldoInicial());
            consulta.setString(3, cuenta.getEstado());
            consulta.setString(4, cuenta.getContraseña());
            consulta.setString(5, cuenta.getCorreoElectronico());
            consulta.setInt(6, cuenta.getPlan_k_plan());
            consulta.executeUpdate();
            logger.info("se actualizó la cuenta " + cuenta.getK_cuenta() + " a " + cuenta.toString());
        } catch (Exception e) {
            logger.warning("No se pudo actualizar la cuenta, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return cuenta;
    }

}
