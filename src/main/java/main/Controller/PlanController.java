package main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.Model.Plan;
import main.Service.PlanService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<List<Plan>> listarPlanes() throws SQLException {
        ResponseEntity response;
        if (planService.listarPlanes() == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(planService.listarPlanes(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPlan(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response = null;
        if (planService.listarPlan(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(planService.listarPlan(id), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/guardar")
    public ResponseEntity guardarPlan(@RequestBody Plan plan) throws SQLException {
        ResponseEntity response;
        if (planService.listarPlan(plan.getK_plan()) != null) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ResponseEntity(planService.guardarPlan(plan), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarPlan(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response;
        if (planService.listarPlan(id) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            planService.eliminarPlan(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Plan> actualizarPlan(@RequestBody Plan plan) throws SQLException {
        ResponseEntity response = null;
        System.out.println(plan);
        if (planService.listarPlan(plan.getK_plan()) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            response = new ResponseEntity(planService.actualizarPlan(plan), HttpStatus.OK);
        }
        return response;
    }

}
