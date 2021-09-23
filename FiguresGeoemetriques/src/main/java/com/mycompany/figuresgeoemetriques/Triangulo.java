
package com.mycompany.figuresgeoemetriques;


public class Triangulo extends Figura{
    //Atributs
    double base;
    double altura;
    
    //Constructor
    public Triangulo(double base, double altura, Punto origen, int id, String borde, String relleno) {
        super(origen, id, borde, relleno);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Triangulo{" + "base=" + base + ", altura=" + altura + '}';
    }  
}
