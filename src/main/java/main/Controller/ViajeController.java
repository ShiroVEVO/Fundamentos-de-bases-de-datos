package main.Controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import main.Model.Estacion;
import main.Model.Viaje;
import main.Repository.Implementations.RelacionViajesDAO;
import main.Service.ViajeService;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

    private ViajeService viajeService;

    @Autowired
    public ViajeController(ViajeService viajeService){
        this.viajeService = viajeService;
    }
    
    @PostMapping("/iniciar")
    public ResponseEntity iniciarViaje(@RequestBody int[] ids) throws SQLException {
        ResponseEntity response;
        int idCuenta = ids[0];
        int idEstacionSalida = ids[1];
        Viaje resultadoViaje = viajeService.empezarViaje(idCuenta, new Timestamp(System.currentTimeMillis()), idEstacionSalida);
        if (resultadoViaje == null) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ResponseEntity(resultadoViaje, HttpStatus.OK);
        }
        //NOTE: hay que guardar el viaje porque con la id del mismo se concluye el viaje
        return response;
    }

    @PostMapping("/terminar")
    public ResponseEntity terminarViaje(@RequestBody int[] ids) throws SQLException {
        ResponseEntity response;
        int idViaje = ids[0];
        int idEstacionLlegada = ids[1];
        if (!viajeService.terminarViaje(new Timestamp(System.currentTimeMillis()), idViaje, idEstacionLlegada)) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            response = new ResponseEntity("Viaje concluido con exito", HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/estacionesEspacio")
    public ResponseEntity<List<Estacion>> listarEstacionesDisponibles() throws SQLException {
        ResponseEntity response;
        List<Estacion> r = viajeService.listarEstacionesConEspacios();
        if (r == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(r, HttpStatus.OK);
        }
        return response;
    }

    @GetMapping("/estacionesBicicleta")
    public ResponseEntity<List<Estacion>> listarEstacionesConBicicletas() throws SQLException {
        ResponseEntity response;
        List<Estacion> r = viajeService.listarEstacionesConBicletas();
        if (r == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(r, HttpStatus.OK);
        }
        return response;
    }
}
