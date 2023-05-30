package main.Model;

public class Localidad {
    int k_localidad,ciudad_k_ciudad;
    String n_localidad;

    public Localidad(int k_localidad, int ciudad_k_ciudad, String n_localidad) {
        this.k_localidad = k_localidad;
        this.ciudad_k_ciudad = ciudad_k_ciudad;
        this.n_localidad = n_localidad;
    }

    public int getK_localidad() {
        return k_localidad;
    }

    public void setK_localidad(int k_localidad) {
        this.k_localidad = k_localidad;
    }

    public int getCiudad_k_ciudad() {
        return ciudad_k_ciudad;
    }

    public void setCiudad_k_ciudad(int ciudad_k_ciudad) {
        this.ciudad_k_ciudad = ciudad_k_ciudad;
    }

    public String getN_localidad() {
        return n_localidad;
    }

    public void setN_localidad(String n_localidad) {
        this.n_localidad = n_localidad;
    }

    @Override
    public String toString() {
        return "Localidad{" +
                "k_localidad=" + k_localidad +
                ", ciudad_k_ciudad=" + ciudad_k_ciudad +
                ", n_localidad='" + n_localidad + '\'' +
                '}';
    }
}
