package main.Repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

public interface IDao<T> {
    public void CrearTabla() throws SQLException;

    public List<T> listarTodos() throws SQLException;

    public T listar(int id) throws SQLException;

    public T agregar(T t) throws SQLException;

    public void eliminar(int id) throws SQLException;

    public T actualizar(T t) throws SQLException;

}
