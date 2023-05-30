package main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import main.Model.Cuenta;
import main.Service.CuentaService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    private final CuentaService cuentaService;

    @Autowired
    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> listarCuentas() throws SQLException { // FUNCIONAL
        ResponseEntity response;
        if (cuentaService.listarCuentas() == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(cuentaService.listarCuentas(), HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCuenta(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response = null;
        if (cuentaService.listarCuenta(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(cuentaService.listarCuenta(id), HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/guardar")
    public ResponseEntity guardarCuenta(@RequestBody Cuenta cuenta) throws SQLException {
        ResponseEntity response;
        if (cuentaService.listarCuenta(cuenta.getK_cuenta()) != null) {
            response = new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        } else {
            response = new ResponseEntity(cuentaService.guardarCuenta(cuenta), HttpStatus.OK);
        }
        return response;
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarCuenta(@PathVariable("id") Integer id) throws SQLException {
        ResponseEntity response;
        if (cuentaService.listarCuenta(id) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            cuentaService.eliminarCuenta(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Cuenta> actualizarCuenta(@RequestBody Cuenta cuenta)
            throws SQLException {
        ResponseEntity response = null;
        if (cuentaService.listarCuenta(cuenta.getK_cuenta()) == null) {
            response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        } else {
            response = new ResponseEntity(cuentaService.actualizarCuenta(cuenta), HttpStatus.OK);
        }
        return response;
    }

}
