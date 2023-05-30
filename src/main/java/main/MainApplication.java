package main;

import java.sql.Timestamp;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.Model.Ciudad;
import main.Model.Plan;
import main.Repository.Implementations.CiudadDAOpostgres;
import main.Repository.Implementations.PlanDAOpostgres;
import main.Repository.Implementations.UsuarioDAOpostgres;
import main.Service.CiudadService;
import main.Service.PlanService;
import main.Service.UsuarioService;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(MainApplication.class, args);

		// ----- PRUEBA BASE DE DATOS (PLAN) -----
		/*
		 * PlanDAOpostgres DAOPlan = new PlanDAOpostgres();
		 * PlanService planService = new PlanService(DAOPlan);
		 * planService.listarPlanes();
		 * planService.listarPlan(3);
		 * Plan plan = new Plan(5, 0, 0, 0, 0, 0, 0, 0, 0, 0, "PlanPrueba");
		 * Plan plan2 = new Plan(5, 1, 1, 1, 1, 1, 1, 1, 2, 3, "PlanCristoTodoLoPuede");
		 * planService.guardarPlan(plan);
		 * planService.actualizarPlan(plan2);
		 * planService.eliminarPlan(5);
		 */

		// ---- PRUEBA BASE DE DATOS (CIUDAD) ----
		CiudadDAOpostgres DAOCiudad = new CiudadDAOpostgres();
		CiudadService ciudadService = new CiudadService(DAOCiudad);
		// ciudadService.listarCiudades();
		// ciudadService.listarCiudad(4);
		// ciudadService.eliminarCiudad(3);
		Timestamp ti = new Timestamp(120319);
		Timestamp tf = new Timestamp(1231041);
		Ciudad c1 = new Ciudad(6, "Cucuta", ti, tf);
		Ciudad c2 = new Ciudad(6, "OIGA MIRE VEA, VENGASE A CALI", ti, tf);
		// ciudadService.guardarCiudad(c1);
		// ciudadService.eliminarCiudad(6);
		// ciudadService.actualizarCiudad(c2);

		// ---- PRUEBA BASE DE DATOS (CUENTA) ----

		// ---- PRUEBA BASE DE DATOS (USUARIO) ----
	}

}
