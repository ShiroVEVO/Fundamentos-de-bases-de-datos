package main.Model;

/**
 * Estructura que representa la tabla de rompimiento entre Viaje, Estacion y Bicicleta
 */
public class RelacionVEB {
    private int idEstacion, idBicicleta, idViaje;
    private boolean entrega;
    
    public RelacionVEB(int idEstacion, int idBicicleta, int idViaje, boolean entrega) {
        this.idEstacion = idEstacion;
        this.idBicicleta = idBicicleta;
        this.idViaje = idViaje;
        this.entrega = entrega;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    
}
