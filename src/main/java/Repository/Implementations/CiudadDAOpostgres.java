package Repository.Implementations;

import Model.Ciudad;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import Repository.IDao;
import Repository.Configuration.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CiudadDAOpostgres implements IDao<Ciudad> {
    private Conexion conexion = new Conexion();
    private PreparedStatement consulta = null;
    private static final Logger logger = Logger.getLogger(CiudadDAOpostgres.class.getName());

    private static final String createTable = "";
    private static final String select = "";
    private static final String select_with_id = "SELECT * FROM ODONTOLOGO WHERE NUMMATRICULA = ?";
    private static final String insert = "INSERT INTO ODONTOLOGO VALUES(?,?,?,?,?,?);";
    private static final String delete = "DELETE FROM ODONTOLOGO WHERE NUMMATRICULA = ?;";
    // private static final String update = "";

}
