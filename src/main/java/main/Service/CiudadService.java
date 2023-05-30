package main.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Model.Bicicleta;
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

    public Bicicleta listarCiudad(int ID) throws SQLException {
        return (Bicicleta) DaoParticular.listar(ID);
    }

    public List<Bicicleta> listarCiudades() throws SQLException {
        return DaoParticular.listarTodos();
    }

    public Bicicleta guardarCiudad(Bicicleta Ciudad) throws SQLException {
        DaoParticular.agregar(Ciudad);
        return Ciudad;
    }

    public void eliminarCiudad(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Bicicleta actualizarCiudad(Bicicleta Ciudad) throws SQLException {
        return (Bicicleta) DaoParticular.actualizar(Ciudad);
    }

}
