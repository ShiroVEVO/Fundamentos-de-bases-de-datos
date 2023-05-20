package Repository.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;

public class Conexion {
    private static final Logger logger = Logger.getLogger(Conexion.class.getName());
    public Connection conn;
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/mydb";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    public void conectar() throws SQLException {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            logger.info("Funciona la conexión, Viva cristo rey");
        } catch (Exception e) {
            logger.info("Problema:" + e);
        }
    }

    public void desconectar() throws SQLException {
        if (conn != null) {
            if (!conn.isClosed()) {
                conn.close();
            }
        }
    }
}