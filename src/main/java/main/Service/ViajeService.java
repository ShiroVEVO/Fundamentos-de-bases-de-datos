package main.Service;

import org.springframework.stereotype.Service;

import main.Controller.ViajeController;
import main.Model.Bicicleta;
import main.Model.Estacion;
import main.Model.RelacionVEB;
import main.Model.Viaje;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import main.Repository.Implementations.EstacionDAOpostgres;
import main.Repository.Implementations.RelacionViajesDAO;
import main.Repository.Implementations.ViajeDAOpostgres;

@Service
public class ViajeService {

    private static final Logger logger = Logger.getLogger(ViajeService.class.getName());

    private ViajeDAOpostgres viajesDao;
    private EstacionDAOpostgres estacionDao;
    private RelacionViajesDAO relDao;

    public ViajeService(){
        viajesDao = new ViajeDAOpostgres();
        estacionDao = new EstacionDAOpostgres();
        relDao = new RelacionViajesDAO();
    }

    public Viaje empezarViaje(int idCuenta, Timestamp f_desbloqueo, int idSalida) throws SQLException {
        
        Estacion estacion = estacionDao.listar(idSalida);

        if(estacion == null || estacion.getAnclajesDisponibles() == estacion.getAnclajesTotales()){
            return null;
        }

        int idBicicleta = relDao.obtenerIdBicicletaDeEstacion(estacion.getK_estacion());
        if(!relDao.desvincularBicletaEstacion(idSalida, idBicicleta)){
            return null;
        }
        // Aumentar disponibles despues de sacar una bicicleta
        estacion.setAnclajesDisponibles(estacion.getAnclajesDisponibles()+1);
        estacionDao.actualizar(estacion);

        int indiceNuevoViaje;
        List<Viaje> viajesExistentes = viajesDao.listarTodos();

        if(viajesExistentes.size() == 0){
            indiceNuevoViaje = 1;
        }
        else {
            indiceNuevoViaje = viajesExistentes.get(viajesExistentes.size()-1).getK_viaje()+1;
        }
        Viaje nuevoViaje = new Viaje(indiceNuevoViaje, idCuenta, null, f_desbloqueo, 0);
        viajesDao.agregar(nuevoViaje);

        relDao.vincularViaje(estacion.getK_estacion(), idBicicleta, nuevoViaje.getK_viaje(), false);

        return nuevoViaje;
    }

    public boolean terminarViaje(Timestamp fEntrega, int idViaje, int idLlegada) throws SQLException{
        Estacion estacion = estacionDao.listar(idLlegada);
        if(estacion.getAnclajesDisponibles() == 0){
            return false;
        }
        Viaje viaje = viajesDao.listar(idViaje);

        if(viaje == null){
            return false;
        }
        
        RelacionVEB relacionVEB = relDao.obtenerVinculoViaje(idViaje);
        if(!relDao.vincularViaje(estacion.getK_estacion(), relacionVEB.getIdBicicleta(), idViaje, true)){
            return false;
        }
        
        if(!relDao.vincularBicicletaEstacion(idLlegada, relacionVEB.getIdBicicleta())) {
            return false;
        }
        viaje.setF_entrega(fEntrega);
        viajesDao.actualizar(viaje);
        estacion.setAnclajesDisponibles(estacion.getAnclajesDisponibles()-1);
        estacionDao.actualizar(estacion);

        return true;
    }
    
    // Lista de salida
    public List<Estacion> listarEstacionesConBicletas() throws SQLException {
        List<Estacion> estacionesExistentes = estacionDao.listarTodos();
        List<Estacion> resultado = new ArrayList<>();

        Iterator<Estacion> it = estacionesExistentes.iterator();

        while(it.hasNext()){
            Estacion aux = it.next();
            if(aux.getAnclajesDisponibles() < aux.getAnclajesTotales()){
                resultado.add(aux);
            }
        }

        return resultado;
    }

    // Lista de llegada
    public List<Estacion> listarEstacionesConEspacios() throws SQLException {
        List<Estacion> estacionesExistentes = estacionDao.listarTodos();
        List<Estacion> resultado = new ArrayList<>();

        Iterator<Estacion> it = estacionesExistentes.iterator();

        while(it.hasNext()){
            Estacion aux = it.next();
            if(aux.getAnclajesDisponibles() > 0){
                resultado.add(aux);
            }
        }

        return resultado;
    }

    
}
