package main;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.Calendar;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Model.Ciudad;
import Repository.Implementations.CiudadDAOpostgres;
import Service.CiudadService;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MainApplication.class, args);
		long x = 21031010;
		Timestamp timestamp = new Timestamp(x);
		Ciudad ciudad = new Ciudad(3, "Santander de quilichao", timestamp, timestamp);
		CiudadDAOpostgres daoP = new CiudadDAOpostgres();
		CiudadService ciudadService = new CiudadService(daoP);
		ciudadService.actualizarCiudad(ciudad);
	}

}
