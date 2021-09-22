
package com.mycompany.figuresgeoemetriques;


public class Cuadrado extends Figura{
    
    //Atributs
    int lado;
    
    //Constructor
    public Cuadrado(Punto origen, int id, String borde, String relleno, int lado) {
        super(origen, id, borde, relleno);
        this.lado = lado;
    }

    @Override
    public String toString() {
        return "Cuadrado{" + "lado=" + lado + '}';
    }
    
    
    
}
