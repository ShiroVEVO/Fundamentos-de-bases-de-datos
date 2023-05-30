package main.Model;

public class Bicicleta {
    int k_bibicleta;
    String tipo;

    public Bicicleta(int k_bibicleta, String tipo) {
        this.k_bibicleta = k_bibicleta;
        this.tipo = tipo;
    }

    public int getK_bibicleta() {
        return k_bibicleta;
    }

    public void setK_bibicleta(int k_bibicleta) {
        this.k_bibicleta = k_bibicleta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                "k_bibicleta=" + k_bibicleta +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
