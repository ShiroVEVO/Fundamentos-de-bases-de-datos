package Model;

import java.sql.Timestamp;

public class Ciudad {
    int IDCiudad;
    String Nombre;
    Timestamp InicioServicio;
    Timestamp FinalServicio;

    public int getIDCiudad() {
        return IDCiudad;
    }

    public void setIDCiudad(int IDCiudad) {
        this.IDCiudad = IDCiudad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Timestamp getInicioServicio() {
        return InicioServicio;
    }

    public void setInicioServicio(Timestamp inicioServicio) {
        InicioServicio = inicioServicio;
    }

    public Timestamp getFinalServicio() {
        return FinalServicio;
    }

    public void setFinalServicio(Timestamp finalServicio) {
        FinalServicio = finalServicio;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "IDCiudad=" + IDCiudad +
                ", Nombre='" + Nombre + '\'' +
                ", InicioServicio=" + InicioServicio +
                ", FinalServicio=" + FinalServicio +
                '}';
    }
}
