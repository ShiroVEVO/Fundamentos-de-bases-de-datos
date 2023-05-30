package main.Model;

public class FormaPago {
    int k_numerotarjeta, usuario_k_numidentificacion;
    String nombreTarjeta, estado, fechaVencimientoTarjeta;

    public FormaPago(int k_numerotarjeta, int usuario_k_numidentificacion, String nombreTarjeta, String estado, String fechaVencimientoTarjeta) {
        this.k_numerotarjeta = k_numerotarjeta;
        this.usuario_k_numidentificacion = usuario_k_numidentificacion;
        this.nombreTarjeta = nombreTarjeta;
        this.estado = estado;
        this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
    }

    public int getK_numerotarjeta() {
        return k_numerotarjeta;
    }

    public void setK_numerotarjeta(int k_numerotarjeta) {
        this.k_numerotarjeta = k_numerotarjeta;
    }

    public int getUsuario_k_numidentificacion() {
        return usuario_k_numidentificacion;
    }

    public void setUsuario_k_numidentificacion(int usuario_k_numidentificacion) {
        this.usuario_k_numidentificacion = usuario_k_numidentificacion;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public void setNombreTarjeta(String nombreTarjeta) {
        this.nombreTarjeta = nombreTarjeta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaVencimientoTarjeta() {
        return fechaVencimientoTarjeta;
    }

    public void setFechaVencimientoTarjeta(String fechaVencimientoTarjeta) {
        this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
    }

    @Override
    public String toString() {
        return "FormaPago{" +
                "k_numerotarjeta=" + k_numerotarjeta +
                ", usuario_k_numidentificacion=" + usuario_k_numidentificacion +
                ", nombreTarjeta='" + nombreTarjeta + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaVencimientoTarjeta='" + fechaVencimientoTarjeta + '\'' +
                '}';
    }
}
