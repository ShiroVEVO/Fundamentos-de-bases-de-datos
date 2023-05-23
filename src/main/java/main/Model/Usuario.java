package main.Model;

import java.security.Timestamp;

public class Usuario {
    int identificacion;
    int numCelular, cuenta_k_cuenta;
    String tipoIdentificacion, nacionalidad, eps, primerNombre, segundoNombre, primerApellido, segundoApellido;
    Timestamp fechaNacimiento;
    char sexo;

    public Usuario(int identificacion, int numCelular, int cuenta_k_cuenta, String tipoIdentificacion,
            String nacionalidad, String eps, String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido, Timestamp fechaNacimiento, char sexo) {
        this.identificacion = identificacion;
        this.numCelular = numCelular;
        this.cuenta_k_cuenta = cuenta_k_cuenta;
        this.tipoIdentificacion = tipoIdentificacion;
        this.nacionalidad = nacionalidad;
        this.eps = eps;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public int getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(int numCelular) {
        this.numCelular = numCelular;
    }

    public int getCuenta_k_cuenta() {
        return cuenta_k_cuenta;
    }

    public void setCuenta_k_cuenta(int cuenta_k_cuenta) {
        this.cuenta_k_cuenta = cuenta_k_cuenta;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "identificacion=" + identificacion +
                ", numCelular=" + numCelular +
                ", cuenta_k_cuenta=" + cuenta_k_cuenta +
                ", tipoIdentificacion='" + tipoIdentificacion + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", eps='" + eps + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo=" + sexo +
                '}';
    }
}
