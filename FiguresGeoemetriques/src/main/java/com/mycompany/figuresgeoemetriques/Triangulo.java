
package com.mycompany.figuresgeoemetriques;


public class Triangulo extends Figura{
    //Atributs
    int hipotenusa;
    
    //Constructor
    public Triangulo(Punto origen,int hipotenusa, int id, String borde, String relleno) {
        super(origen, id, borde, relleno);
        this.hipotenusa = hipotenusa;
        
    }

    @Override
    public String toString() {
        return "Triangulo{" + "lado1=" + hipotenusa + ", lado2=" + hipotenusa + '}';
    }
    
    
}
