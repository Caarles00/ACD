
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
    public static void area(){
        
    }
    
    public static void perimetro(){
        
    }
    
    public static void distancia(Figura f){
        
    }
    
    public static void escalar(int porcentaje){
        
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
