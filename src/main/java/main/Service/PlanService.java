package main.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import main.Model.Plan;
import main.Repository.IDao;
import main.Repository.Implementations.PlanDAOpostgres;

@Service
public class PlanService {
    private IDao DaoParticular;

    @Autowired
    public PlanService(IDao DAOpart) {
        this.DaoParticular = new PlanDAOpostgres();
    }

    public void crearTabla() throws SQLException {
        DaoParticular.CrearTabla();
    }

    public Plan listarPlan(int ID) throws SQLException {
        return (Plan) DaoParticular.listar(ID);
    }

    public List<Plan> listarPlanes() throws SQLException {
        return DaoParticular.listarTodos();
    }

    public Plan guardarPlan(Plan plan) throws SQLException {
        DaoParticular.agregar(plan);
        return plan;
    }

    public void eliminarPlan(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Plan actualizarPlan(Plan plan) throws SQLException {
        return (Plan) DaoParticular.actualizar(plan);
    }

}
