package main.Repository.Implementations;

import main.Model.Plan;
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
public class PlanDAOpostgres implements IDao<Plan> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(PlanDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Plan("
            + "k_Plan INT NOT NULL,"
            + "i_ValorRetiroMecanica FLOAT NOT NULL,"
            + "i_ValorRetiroElectrica FLOAT NOT NULL,"
            + "i_TarifaSuscripcion FLOAT NOT NULL,"
            + "i_TiempoSuscripcion INT NULL,"
            + "i_DuracionMaxViaje INT NULL,"
            + "i_CantidadMaxViajes INT NULL,"
            + "q_ViajesExtra INT NULL,"
            + "i_ValorViajeExtra FLOAT NULL,"
            + "i_ValorMinAdicional FLOAT NOT NULL,"
            + "n_nombre VARCHAR(45) NOT NULL,"
            + "PRIMARY KEY (k_Plan));";
    private static final String select = "SELECT * FROM mydb.Plan;";
    private static final String select_with_id = "SELECT * FROM mydb.Plan WHERE k_plan = ?;";
    private static final String insert = "INSERT INTO mydb.Plan VALUES(?,?,?,?,?,?,?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.Plan WHERE k_plan = ?;";
    private static final String update = "UPDATE mydb.plan SET i_valorretiromecanica = ?, i_valorretiroelectrica = ?,"
            + "i_tarifasuscripcion = ?, i_tiemposuscripcion = ?, i_duracionmaxviaje = ?,"
            + "i_cantidadmaxviajes = ?, q_viajesextra = ?, i_valorviajeextra = ?, i_valorminadicional = ?, n_nombre = ? "
            + "WHERE k_plan = ?;";

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
    public List<Plan> listarTodos() throws SQLException { // FUNCIONAL
        Statement consulta = null;
        ResultSet resultados = null;
        List<Plan> ListaPlanes = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                //
                int k_plan = resultados.getInt(1);
                double valorRetiroMecanica = resultados.getDouble(2);
                double valorRetiroElectrica = resultados.getDouble(3);
                double tarifaSuscripcion = resultados.getDouble(4);
                int tiempoSuscripcion = resultados.getInt(5);
                int duracionMaxViaje = resultados.getInt(6);
                int cantidadMaxViajes = resultados.getInt(7);
                int viajesExtra = resultados.getInt(8);
                double valorViajeExtra = resultados.getDouble(9);
                double valorMinAdicional = resultados.getDouble(10);
                String nombre = resultados.getString(11);
                Plan plan = new Plan(k_plan, tiempoSuscripcion, duracionMaxViaje, cantidadMaxViajes, viajesExtra,
                        valorRetiroMecanica, valorRetiroElectrica, tarifaSuscripcion, valorViajeExtra,
                        valorMinAdicional, nombre);
                logger.info("Se trajo un plan: " + plan);
                ListaPlanes.add(plan);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar los planes, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaPlanes;
    }

    @Override
    public Plan listar(int id) throws SQLException { // FUNCIONAL
        ResultSet resultados = null;
        Plan plan = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                //
                int k_plan = resultados.getInt(1);
                double valorRetiroMecanica = resultados.getDouble(2);
                double valorRetiroElectrica = resultados.getDouble(3);
                double tarifaSuscripcion = resultados.getDouble(4);
                int tiempoSuscripcion = resultados.getInt(5);
                int duracionMaxViaje = resultados.getInt(6);
                int cantidadMaxViajes = resultados.getInt(7);
                int viajesExtra = resultados.getInt(8);
                double valorViajeExtra = resultados.getDouble(9);
                double valorMinAdicional = resultados.getDouble(10);
                String nombre = resultados.getString(11);
                plan = new Plan(k_plan, tiempoSuscripcion, duracionMaxViaje, cantidadMaxViajes, viajesExtra,
                        valorRetiroMecanica, valorRetiroElectrica, tarifaSuscripcion, valorViajeExtra,
                        valorMinAdicional, nombre);
                logger.info("Se trajo el plan con id: " + id + ": " + plan);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer el plan con id: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return plan;
    }

    @Override
    public Plan agregar(Plan plan) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, plan.getK_plan());
            consulta.setDouble(2, plan.getValorRetiroMecanica());
            consulta.setDouble(3, plan.getValorRetiroElectrica());
            consulta.setDouble(4, plan.getTarifaSuscripcion());
            consulta.setInt(5, plan.getTiempoSuscripcion());
            consulta.setInt(6, plan.getDuracionMaxViaje());
            consulta.setInt(7, plan.getCantidadMaxViajes());
            consulta.setInt(8, plan.getViajesExtra());
            consulta.setDouble(9, plan.getValorViajeExtra());
            consulta.setDouble(10, plan.getValorMinAdicional());
            consulta.setString(11, plan.getNombre());
            consulta.execute();
            logger.info("Se guardo el plan:" + plan.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar el plan, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return plan;
    }

    @Override
    public void eliminar(int id) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó el plan con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar el plan, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Plan actualizar(Plan plan) throws SQLException { // FUNCIONAL
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            //
            consulta.setDouble(1, plan.getValorRetiroMecanica());
            consulta.setDouble(2, plan.getValorRetiroElectrica());
            consulta.setDouble(3, plan.getTarifaSuscripcion());
            consulta.setInt(4, plan.getTiempoSuscripcion());
            consulta.setInt(5, plan.getDuracionMaxViaje());
            consulta.setInt(6, plan.getCantidadMaxViajes());
            consulta.setInt(7, plan.getViajesExtra());
            consulta.setDouble(8, plan.getValorViajeExtra());
            consulta.setDouble(9, plan.getValorMinAdicional());
            consulta.setString(10, plan.getNombre());
            consulta.setInt(11, plan.getK_plan());
            consulta.executeUpdate();
            logger.info("se actualizó el plan " + plan.getK_plan() + " a " + plan.toString());
        } catch (Exception e) {
            logger.warning("No se pudo actualizar el plan, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return plan;
    }

}
