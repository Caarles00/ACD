
package com.mycompany.figuresgeoemetriques;


public abstract class Figura {
    
    //Atributs
    Punto origen; 
    int id;
    String borde;
    String relleno;
    
    //Constructor
    public Figura(Punto origen, int id, String borde, String relleno) {
        this.origen = origen;
        this.id = id;
        this.borde = borde;
        this.relleno = relleno;
    }
    
    
    //Getters i setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorde() {
        return borde;
    }

    public void setBorde(String borde) {
        this.borde = borde;
    }

    public String getRelleno() {
        return relleno;
    }

    public void setRelleno(String relleno) {
        this.relleno = relleno;
    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto origen) {
        this.origen = origen;
    }
    
    
    //Metodes
    public static double areaCuadrado(double lado){
        return lado*lado;
    }
    public static double perimetroCuadrado(double lado){
        return lado*4;
    }
    
    
    public static double areaRectangulo(double base, double altura){
        return base*altura;
    }
    
    public static double perimetroRectangulo(double base, double altura){
        return (base*2) + (altura*2);
    }
    
    public static double areaCirculo(double radio){
        return Math.pow(radio, 2)*Math.PI;
    }
    
    public static double perimetroCirculo(double radio){
        return radio*2*Math.PI;
    }
    
    public static double areaTriangulo(double base, double altura){
        return (base*altura)/2;
    }
    
    public static double perimetroTriangulo(double base, double altura){
        return Math.sqrt(Math.pow(base, 2)+Math.pow(altura, 2));
    }
    
    public static double escalar(int porcentaje){
        double percent = porcentaje/100;
        return percent;
    }
    
    public static void mover(Punto p){
        
    }
    
    public static void desplazarh(double x){
        
    }
    
    public static void desplazary(double y){
        
    }
    
    //toString
    @Override
    public String toString() {
        return "Figura{" + "id=" + id + ", borde=" + borde + ", relleno=" + relleno + '}';
    }
    
    
}
