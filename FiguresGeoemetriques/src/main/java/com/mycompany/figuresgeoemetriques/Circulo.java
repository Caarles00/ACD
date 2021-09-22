
package com.mycompany.figuresgeoemetriques;


public class Circulo extends Figura{
    
    //Atributs
    int radio;
    
    //Constructor
    public Circulo(Punto origen, int id, String borde, String relleno, int radio) {
        super(origen, id, borde, relleno);
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "Circulo{" + "radio=" + radio + '}';
    }
    
    
}
