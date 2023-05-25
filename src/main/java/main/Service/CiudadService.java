package main.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import main.Model.Ciudad;
import main.Repository.IDao;
import main.Repository.Implementations.CiudadDAOpostgres;

@Service
public class CiudadService {
    private IDao DaoParticular;

    @Autowired
    public CiudadService(IDao DAOpart) {
        this.DaoParticular = new CiudadDAOpostgres(); // INYECCIÓN DEL DAO
    }

    public void crearTabla() throws SQLException {
        DaoParticular.CrearTabla();
    }

    public Ciudad listarCiudad(int ID) throws SQLException {
        return (Ciudad) DaoParticular.listar(ID);
    }

    public List<Ciudad> listarCiudades() throws SQLException {
        return DaoParticular.listarTodos();
    }

    public Ciudad guardarCiudad(Ciudad Ciudad) throws SQLException {
        DaoParticular.agregar(Ciudad);
        return Ciudad;
    }

    public void eliminarCiudad(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Ciudad actualizarCiudad(Ciudad Ciudad) throws SQLException {
        return (Ciudad) DaoParticular.actualizar(Ciudad);
    }

}
