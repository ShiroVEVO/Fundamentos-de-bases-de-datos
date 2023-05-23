package main.Model;

public class Cuenta {
    int k_cuenta, plan_k_plan;
    double saldoFinal, saldoInicial;
    String estado, contraseña, correoElectronico;

    public Cuenta(int k_cuenta, int plan_k_plan, double saldoFinal, double saldoInicial, String estado,
            String contraseña,
            String correoElectronico) {
        this.k_cuenta = k_cuenta;
        this.plan_k_plan = plan_k_plan;
        this.saldoFinal = saldoFinal;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.contraseña = contraseña;
        this.correoElectronico = correoElectronico;
    }

    public int getK_cuenta() {
        return k_cuenta;
    }

    public void setK_cuenta(int k_cuenta) {
        this.k_cuenta = k_cuenta;
    }

    public int getPlan_k_plan() {
        return plan_k_plan;
    }

    public void setPlan_k_plan(int plan_k_plan) {
        this.plan_k_plan = plan_k_plan;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "k_cuenta=" + k_cuenta +
                ", plan_k_plan=" + plan_k_plan +
                ", saldoFinal=" + saldoFinal +
                ", saldoInicial=" + saldoInicial +
                ", estado='" + estado + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
