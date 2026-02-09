package punto;

public class CalculadoraVectores {

    public VectorR2 calculcarPosicionVector(Punto p1, Punto p2){
        double vx = p2.getX() - p1.getX();
        double vy = p2.getY() - p1.getY();
        return new VectorR2(vy, vx);
    }

    public double calcularMagnitud(VectorR2 v){
        return Math.hypot(v.getX(), v.getY());
    }

    public double calcularDireccion(VectorR2 v){
        return Math.atan2(v.getY(), v.getX());
    }

    public VectorR2 trasladarOrigen(VectorR2 v){
        if (v.getPuntoInicial() == null) return v;

        double vx = v.getPuntoFinal().getX() - v.getPuntoFinal().getX();
        double vy = v.getPuntoFinal().getY() - v.getPuntoInicial().getY();
        return new VectorR2(vy, vx);

    }
    public void mostrarInfoVector(VectorR2 v) {
        System.out.println("--- Análisis de Vector ---");
        System.out.println("Componentes: (" + v.getX() + ", " + v.getY() + ")");
        System.out.println("Magnitud: " + calcularMagnitud(v));
        System.out.println("Dirección (Grados): " + Math.toDegrees(calcularDireccion(v)) + "°");
    }


}
