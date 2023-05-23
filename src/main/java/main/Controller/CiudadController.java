package main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.Model.Ciudad;
import main.Service.CiudadService;

import java.sql.SQLException;
import java.util.List;

@RestController

@RequestMapping("/ciudad")
public class CiudadController {
    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public ResponseEntity<List<Ciudad>> listarCiudades() throws SQLException {
        ResponseEntity response;
        if (ciudadService.listarCiudades() == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(ciudadService.listarCiudades(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCiudad(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response = null;
        if (ciudadService.listarCiudad(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(ciudadService.listarCiudad(id), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/guardar")
    public ResponseEntity guardarCiudad(@RequestBody Ciudad ciudad) throws SQLException {
        ResponseEntity response;
        if (ciudadService.listarCiudad(ciudad.getIDCiudad()) != null) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ResponseEntity(ciudadService.guardarCiudad(ciudad), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarCiudad(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response;
        if (ciudadService.listarCiudad(id) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            ciudadService.eliminarCiudad(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Ciudad> actualizarCiudad(@RequestBody Ciudad ciudad)
            throws SQLException {
        ResponseEntity response = null;
        System.out.println(ciudad);
        if (ciudadService.listarCiudad(ciudad.getIDCiudad()) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            response = new ResponseEntity(ciudadService.actualizarCiudad(ciudad), HttpStatus.OK);
        }
        return response;
    }

}
