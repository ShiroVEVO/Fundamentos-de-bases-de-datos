package Repository;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public void CrearTabla() throws SQLException;

    public List<T> listarTodos() throws SQLException;

    public T listar(int id) throws SQLException;

    public void agregar(T t) throws SQLException;s

    public void eliminar(int id) throws SQLException;

    public T actualizar(T t) throws SQLException;

}
