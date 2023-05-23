package main;

import java.sql.Timestamp;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.Model.Plan;
import main.Repository.Implementations.PlanDAOpostgres;
import main.Repository.Implementations.UsuarioDAOpostgres;
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
		 * Plan plan2 = new Plan(5, 1, 1, 1, 1, 1, 1, 1, 2, 3, "PlanPruebaUpdate");
		 * planService.guardarPlan(plan);
		 * planService.eliminarPlan(5);
		 * planService.actualizarPlan(plan2);
		 */

		// ---- PRUEBA BASE DE DATOS (CUENTA) ----

	}

}
