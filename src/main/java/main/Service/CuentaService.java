package main.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Model.Cuenta;
import main.Repository.IDao;
import main.Repository.Implementations.CuentaDAOpostgres;

@Service
public class CuentaService {
    @Autowired
    private IDao DaoParticular;

    public CuentaService(IDao DAOpart) {
        this.DaoParticular = new CuentaDAOpostgres();
    }

    public void crearTabla() throws SQLException {
        DaoParticular.CrearTabla();
    }

    public Cuenta listarCuenta(int ID) throws SQLException {
        return (Cuenta) DaoParticular.listar(ID);
    }

    public List<Cuenta> listarCuentas() throws SQLException {
        return DaoParticular.listarTodos();
    }

    public Cuenta guardarCuenta(Cuenta cuenta) throws SQLException {
        DaoParticular.agregar(cuenta);
        return cuenta;
    }

    public void eliminarCuenta(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Cuenta actualizarCuenta(Cuenta cuenta) throws SQLException {
        return (Cuenta) DaoParticular.actualizar(cuenta);
    }

}
