package punto;

public class VectorR2 {

    private double x;

    private double y;


    private Punto puntoInicial;

    private Punto puntoFinal;


    public VectorR2() {
    }

    public VectorR2(Punto puntoInicial, Punto puntoFinal) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public VectorR2(double y, double x) {
        this.y = y;
        this.x = x;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Punto getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(Punto puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public Punto getPuntoInicial() {
        return puntoInicial;
    }

    public void setPuntoInicial(Punto puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public double getMagnitud(double x, double y){

        if (puntoInicial != null && puntoFinal != null){
            double deltaX = puntoFinal.getX() - puntoInicial.getX();
            double deltaY = puntoFinal.getY() - puntoInicial.getY();

            return Math.hypot(deltaX, deltaY);
        }

        return Math.hypot(this.x, this.y);

    }


    public double getDireccionRad(){
        double compX;
        double compY;

        if (this.puntoInicial != null && this.puntoFinal != null){
            compX = puntoFinal.getX() - puntoInicial.getX();
            compY = puntoFinal.getY() - puntoInicial.getY();
        }else {
            compX = this.x;
            compY = this.y;
        }

        return Math.atan2(compY,compX);

    }

    public double getDireccionGrados(){
        double radianes = getDireccionRad();
        return Math.toDegrees(radianes);
    }


}
