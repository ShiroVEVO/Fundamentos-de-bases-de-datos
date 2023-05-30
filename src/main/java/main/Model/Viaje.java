package main.Model;

import java.sql.Timestamp;

public class Viaje {
    int k_viaje, cuenta_k_cuenta;
    Timestamp f_entrega,f_desbloqueo;
    double costo;

    public Viaje(int k_viaje, int cuenta_k_cuenta, Timestamp f_entrega, Timestamp f_desbloqueo, double costo) {
        this.k_viaje = k_viaje;
        this.cuenta_k_cuenta = cuenta_k_cuenta;
        this.f_entrega = f_entrega;
        this.f_desbloqueo = f_desbloqueo;
        this.costo = costo;
    }

    public int getK_viaje() {
        return k_viaje;
    }

    public void setK_viaje(int k_viaje) {
        this.k_viaje = k_viaje;
    }

    public int getCuenta_k_cuenta() {
        return cuenta_k_cuenta;
    }

    public void setCuenta_k_cuenta(int cuenta_k_cuenta) {
        this.cuenta_k_cuenta = cuenta_k_cuenta;
    }

    public Timestamp getF_entrega() {
        return f_entrega;
    }

    public void setF_entrega(Timestamp f_entrega) {
        this.f_entrega = f_entrega;
    }

    public Timestamp getF_desbloqueo() {
        return f_desbloqueo;
    }

    public void setF_desbloqueo(Timestamp f_desbloqueo) {
        this.f_desbloqueo = f_desbloqueo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Viaje{" +
                "k_viaje=" + k_viaje +
                ", cuenta_k_cuenta=" + cuenta_k_cuenta +
                ", f_entrega=" + f_entrega +
                ", f_desbloqueo=" + f_desbloqueo +
                ", costo=" + costo +
                '}';
    }
}
