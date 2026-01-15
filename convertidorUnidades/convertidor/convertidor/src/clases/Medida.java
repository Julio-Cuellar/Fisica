package clases;

public class Medida {

    private String nombre;

    private String abreviatura;

    private String sistema;

    private double valor;


    public Medida(String nombre, String abreviatura, String sistema, double valor) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.sistema = sistema;
        this.valor = valor;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }



    @Override
    public String toString(){
        return valor + " " + abreviatura;
    }
}
