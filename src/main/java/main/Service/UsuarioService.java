package main.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Model.Usuario;
import main.Repository.IDao;
import main.Repository.Implementations.UsuarioDAOpostgres;

@Service
public class UsuarioService {
    private IDao DaoParticular;

    @Autowired
    public UsuarioService(IDao DAOpart) {
        this.DaoParticular = new UsuarioDAOpostgres();
    }

    public void crearTabla() throws SQLException {
        DaoParticular.CrearTabla();
    }

    public Usuario listarUsuario(int ID) throws SQLException {
        return (Usuario) DaoParticular.listar(ID);
    }

    public List<Usuario> listarUsuarios() throws SQLException {
        return DaoParticular.listarTodos();
    }

    public Usuario guardarUsuario(Usuario usuario) throws SQLException {
        DaoParticular.agregar(usuario);
        return usuario;
    }

    public void eliminarUsuario(int ID) throws SQLException {
        DaoParticular.eliminar(ID);
    }

    public Usuario actualizarUsuario(Usuario usuario) throws SQLException {
        return (Usuario) DaoParticular.actualizar(usuario);
    }

}
