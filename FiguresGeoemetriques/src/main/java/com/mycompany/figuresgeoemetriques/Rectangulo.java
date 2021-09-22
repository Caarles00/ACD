
package com.mycompany.figuresgeoemetriques;


public class Rectangulo extends Figura{
    
    //Atributs
    int base, altura;
    
    //Constructor
    public Rectangulo(Punto origen, int id, String borde, String relleno, int base, int altura) {
        super(origen, id, borde, relleno);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public String toString() {
        return "Rectangulo{" + "base=" + base + ", altura=" + altura + '}';
    }
    
}
