package main.Model;

public class Estacion {
    int k_estacion, anclajesDisponibles,anclajesTotales,localidad_k_localidad;
    String direccion;

    public Estacion(int k_estacion, int anclajesDisponibles, int anclajesTotales, int localidad_k_localidad, String direccion) {
        this.k_estacion = k_estacion;
        this.anclajesDisponibles = anclajesDisponibles;
        this.anclajesTotales = anclajesTotales;
        this.localidad_k_localidad = localidad_k_localidad;
        this.direccion = direccion;
    }

    public int getK_estacion() {
        return k_estacion;
    }

    public void setK_estacion(int k_estacion) {
        this.k_estacion = k_estacion;
    }

    public int getAnclajesDisponibles() {
        return anclajesDisponibles;
    }

    public void setAnclajesDisponibles(int anclajesDisponibles) {
        this.anclajesDisponibles = anclajesDisponibles;
    }

    public int getAnclajesTotales() {
        return anclajesTotales;
    }

    public void setAnclajesTotales(int anclajesTotales) {
        this.anclajesTotales = anclajesTotales;
    }

    public int getLocalidad_k_localidad() {
        return localidad_k_localidad;
    }

    public void setLocalidad_k_localidad(int localidad_k_localidad) {
        this.localidad_k_localidad = localidad_k_localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "FormaPago{" +
                "k_estacion=" + k_estacion +
                ", anclajesDisponibles=" + anclajesDisponibles +
                ", anclajesTotales=" + anclajesTotales +
                ", localidad_k_localidad=" + localidad_k_localidad +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
