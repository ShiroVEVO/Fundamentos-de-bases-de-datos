package Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import Model.Ciudad;
import Repository.IDao;

@Service
public class CiudadService {
    private IDao DaoParticular;

    public CiudadService(IDao DAOpart) {
        this.DaoParticular = DAOpart;
    }

    public void crearTabla() throws SQLException {
        DaoParticular.CrearTabla();
    }

    public Ciudad listarCiudad(int ID) throws SQLException {
        return (Ciudad) DaoParticular.listar(ID);
    }

    public void guardarCiudad(Ciudad Ciudad) throws SQLException {
        DaoParticular.agregar(Ciudad);
    }

    public void eliminarCiudad(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Ciudad actualizarCiudad(Ciudad Ciudad) throws SQLException {
        return (Ciudad) DaoParticular.actualizar(Ciudad);
    }

    public List listarCiudades() throws SQLException {
        return DaoParticular.listarTodos();
    }
}
