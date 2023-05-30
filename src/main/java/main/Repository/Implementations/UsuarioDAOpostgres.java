package main.Repository.Implementations;

import main.Model.Usuario;
import main.Repository.IDao;
import main.Repository.Configuration.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@Service
public class UsuarioDAOpostgres implements IDao<Usuario> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(UsuarioDAOpostgres.class.getName());

    private static final String createTable = "CREATE TABLE IF NOT EXISTS mydb.Usuario ("
            + "k_NumIdentificacion INT NOT NULL,"
            + "n_TipoIdentificacion VARCHAR(15) NOT NULL,"
            + "f_fechaNacimiento TIMESTAMP NOT NULL,"
            + "n_Nacionalidad VARCHAR(20) NOT NULL,"
            + "v_NumCelular BIGINT NOT NULL,"
            + "i_Sexo CHAR(1) NOT NULL,"
            + "n_Eps VARCHAR(20) NOT NULL,"
            + "n_PrimerNombre VARCHAR(25) NOT NULL,"
            + "n_SegundoNombre VARCHAR(25) NULL,"
            + "n_PrimerApellido VARCHAR(25) NOT NULL,"
            + "n_SegundoApellido VARCHAR(25) NULL,"
            + "Cuenta_k_Cuenta INT NOT NULL,"
            + "PRIMARY KEY (k_NumIdentificacion),"
            + "CONSTRAINT fk_Usuario_Cuenta1"
            + "FOREIGN KEY (Cuenta_k_Cuenta)"
            + "REFERENCES mydb.Cuenta (k_Cuenta)"
            + "ON DELETE NO ACTION"
            + "ON UPDATE NO ACTION);"
            + "CREATE INDEX fk_Usuario_Cuenta1_idx"
            + "ON mydb.Usuario (Cuenta_k_Cuenta);";
    private static final String select = "SELECT * FROM mydb.usuario;";
    private static final String select_with_id = "SELECT * FROM mydb.usuario WHERE k_numidentificacion = ?;";
    private static final String insert = "INSERT INTO mydb.usuario VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String delete = "DELETE FROM mydb.usuario WHERE k_numidentificacion = ?;";
    private static final String update = "UPDATE mydb.usuario SET n_tipoidentificacion = ?, f_fechanacimiento = ?,"
            + "n_nacionalidad = ?, v_numcelular = ?, i_sexo = ?, n_eps = ?,"
            + "n_primernombre = ?, n_segundonombre = ?, n_primerapellido = ?,"
            + "n_segundoapellido = ?, cuenta_k_cuenta = ? WHERE k_numidentificacion = ?;";

    @Override
    public void CrearTabla() throws SQLException {
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
    public List<Usuario> listarTodos() throws SQLException {
        Statement consulta = null;
        ResultSet resultados = null;
        List<Usuario> ListaUsuarios = new ArrayList<>();
        try {
            conexion.conectar();
            consulta = conexion.conn.createStatement();
            resultados = consulta.executeQuery(select);

            while (resultados.next()) {
                int numIdentificacion = resultados.getInt(1);
                String tipoIdentificacion = resultados.getString(2);
                java.sql.Timestamp fechaNacimiento = resultados.getTimestamp(3);
                String nacionalidad = resultados.getString(4);
                Long numCelular = resultados.getLong(5);
                char sexo = resultados.getString(6).charAt(0);
                String eps = resultados.getString(7);
                String primerNombre = resultados.getString(8);
                String segundoNombre = resultados.getString(9);
                String primerApellido = resultados.getString(10);
                String segundoApellido = resultados.getString(11);
                int cuenta_k_cuenta = resultados.getInt(12);
                Usuario usuario = new Usuario(numIdentificacion, numCelular, cuenta_k_cuenta, tipoIdentificacion,
                        nacionalidad, eps, primerNombre, segundoNombre, primerApellido, segundoApellido,
                        fechaNacimiento, sexo);
                logger.info("Se trajo un usuario: " + usuario);
                ListaUsuarios.add(usuario);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al listar usuarios, " + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return ListaUsuarios;
    }

    @Override
    public Usuario listar(int id) throws SQLException {
        ResultSet resultados = null;
        Usuario usuario = null;
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(select_with_id);
            consulta.setInt(1, id);
            resultados = consulta.executeQuery();
            if (resultados.next()) {
                int numIdentificacion = resultados.getInt(1);
                String tipoIdentificacion = resultados.getString(2);
                java.sql.Timestamp fechaNacimiento = resultados.getTimestamp(3);
                String nacionalidad = resultados.getString(4);
                Long numCelular = resultados.getLong(5);
                char sexo = resultados.getString(6).charAt(0);
                String eps = resultados.getString(7);
                String primerNombre = resultados.getString(8);
                String segundoNombre = resultados.getString(9);
                String primerApellido = resultados.getString(10);
                String segundoApellido = resultados.getString(11);
                int cuenta_k_cuenta = resultados.getInt(12);
                usuario = new Usuario(numIdentificacion, numCelular, cuenta_k_cuenta, tipoIdentificacion, nacionalidad,
                        eps, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, sexo);
                logger.info("Se trajo el usuario con identificacion: " + numIdentificacion + ": " + usuario);
            }
        } catch (Exception e) {
            logger.info("Se presento un error al traer el usuario con identificacion: " + id + " ," + e);
        } finally {
            resultados.close();
            consulta.close();
            conexion.desconectar();
        }
        return usuario;
    }

    @Override
    public Usuario agregar(Usuario usuario) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(insert);
            consulta.setInt(1, usuario.getIdentificacion());
            consulta.setString(2, usuario.getTipoIdentificacion());
            consulta.setTimestamp(3, usuario.getFechaNacimiento());
            consulta.setString(4, usuario.getNacionalidad());
            consulta.setLong(5, usuario.getNumCelular());
            consulta.setString(6, String.valueOf(usuario.getSexo()));
            consulta.setString(7, usuario.getEps());
            consulta.setString(8, usuario.getPrimerNombre());
            consulta.setString(9, usuario.getSegundoNombre());
            consulta.setString(10, usuario.getPrimerApellido());
            consulta.setString(11, usuario.getSegundoApellido());
            consulta.setInt(12, usuario.getCuenta_k_cuenta());
            consulta.execute();
            logger.info("Se guardo el usuario:" + usuario.toString());
        } catch (Exception e) {
            logger.warning("No se pudo guardar el usuario, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return usuario;
    }

    @Override
    public void eliminar(int id) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(delete);
            consulta.setInt(1, id);
            consulta.executeUpdate();
            logger.info("Se eliminó el usuario con ID: " + id);
        } catch (Exception e) {
            logger.warning("No se pudo eliminar el usuario, " + e);
        } finally {
            conexion.desconectar();
        }
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        try {
            conexion.conectar();
            consulta = conexion.conn.prepareStatement(update);
            consulta.setInt(12, usuario.getIdentificacion());
            consulta.setString(1, usuario.getTipoIdentificacion());
            consulta.setTimestamp(2, usuario.getFechaNacimiento());
            consulta.setString(3, usuario.getNacionalidad());
            consulta.setLong(4, usuario.getNumCelular());
            consulta.setString(5, String.valueOf(usuario.getSexo()));
            consulta.setString(6, usuario.getEps());
            consulta.setString(7, usuario.getPrimerNombre());
            consulta.setString(8, usuario.getSegundoNombre());
            consulta.setString(9, usuario.getPrimerApellido());
            consulta.setString(10, usuario.getSegundoApellido());
            consulta.setInt(11, usuario.getCuenta_k_cuenta());
            consulta.executeUpdate();
            logger.info("se actualizó el usuario " + usuario.getIdentificacion() + " a " + usuario.toString());
        } catch (Exception e) {
            logger.warning("No se pudo actualizar el usuario, " + e);
        } finally {
            consulta.close();
            conexion.desconectar();
        }
        return usuario;
    }

}
