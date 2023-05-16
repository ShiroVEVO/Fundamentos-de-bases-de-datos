package main;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Repository.Configuration.Conexion;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MainApplication.class, args);
		Conexion conexion = new Conexion();
		conexion.conectar();
	}

}
