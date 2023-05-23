package main.Model;

public class Plan {
    int k_plan, tiempoSuscripcion, duracionMaxViaje, cantidadMaxViajes, viajesExtra;
    double valorRetiroMecanica, valorRetiroElectrica, tarifaSuscripcion, valorViajeExtra, valorMinAdicional;
    String nombre;

    public Plan(int k_plan, int tiempoSuscripcion, int duracionMaxViaje, int cantidadMaxViajes, int viajesExtra,
            double valorRetiroMecanica, double valorRetiroElectrica, double tarifaSuscripcion, double valorViajeExtra,
            double valorMinAdicional, String nombre) {
        this.k_plan = k_plan;
        this.tiempoSuscripcion = tiempoSuscripcion;
        this.duracionMaxViaje = duracionMaxViaje;
        this.cantidadMaxViajes = cantidadMaxViajes;
        this.viajesExtra = viajesExtra;
        this.valorRetiroMecanica = valorRetiroMecanica;
        this.valorRetiroElectrica = valorRetiroElectrica;
        this.tarifaSuscripcion = tarifaSuscripcion;
        this.valorViajeExtra = valorViajeExtra;
        this.valorMinAdicional = valorMinAdicional;
        this.nombre = nombre;
    }

    public int getK_plan() {
        return k_plan;
    }

    public void setK_plan(int k_plan) {
        this.k_plan = k_plan;
    }

    public int getTiempoSuscripcion() {
        return tiempoSuscripcion;
    }

    public void setTiempoSuscripcion(int tiempoSuscripcion) {
        this.tiempoSuscripcion = tiempoSuscripcion;
    }

    public int getDuracionMaxViaje() {
        return duracionMaxViaje;
    }

    public void setDuracionMaxViaje(int duracionMaxViaje) {
        this.duracionMaxViaje = duracionMaxViaje;
    }

    public int getCantidadMaxViajes() {
        return cantidadMaxViajes;
    }

    public void setCantidadMaxViajes(int cantidadMaxViajes) {
        this.cantidadMaxViajes = cantidadMaxViajes;
    }

    public int getViajesExtra() {
        return viajesExtra;
    }

    public void setViajesExtra(int viajesExtra) {
        this.viajesExtra = viajesExtra;
    }

    public double getValorRetiroMecanica() {
        return valorRetiroMecanica;
    }

    public void setValorRetiroMecanica(double valorRetiroMecanica) {
        this.valorRetiroMecanica = valorRetiroMecanica;
    }

    public double getValorRetiroElectrica() {
        return valorRetiroElectrica;
    }

    public void setValorRetiroElectrica(double valorRetiroElectrica) {
        this.valorRetiroElectrica = valorRetiroElectrica;
    }

    public double getTarifaSuscripcion() {
        return tarifaSuscripcion;
    }

    public void setTarifaSuscripcion(double tarifaSuscripcion) {
        this.tarifaSuscripcion = tarifaSuscripcion;
    }

    public double getValorViajeExtra() {
        return valorViajeExtra;
    }

    public void setValorViajeExtra(double valorViajeExtra) {
        this.valorViajeExtra = valorViajeExtra;
    }

    public double getValorMinAdicional() {
        return valorMinAdicional;
    }

    public void setValorMinAdicional(double valorMinAdicional) {
        this.valorMinAdicional = valorMinAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "k_plan=" + k_plan +
                ", tiempoSuscripcion=" + tiempoSuscripcion +
                ", duracionMaxViaje=" + duracionMaxViaje +
                ", cantidadMaxViajes=" + cantidadMaxViajes +
                ", viajesExtra=" + viajesExtra +
                ", valorRetiroMecanica=" + valorRetiroMecanica +
                ", valorRetiroElectrica=" + valorRetiroElectrica +
                ", tarifaSuscripcion=" + tarifaSuscripcion +
                ", valorViajeExtra=" + valorViajeExtra +
                ", valorMinAdicional=" + valorMinAdicional +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
