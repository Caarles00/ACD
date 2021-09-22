//Carles Montilla Rodrigo 2DAM
package com.mycompany.figuresgeoemetriques;

import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);
        
        int op, opSubMenu;
        boolean seguir = true, seguirSubMenu = true;
        
        do{
            System.out.println("========================");
            System.out.println("  FIGURES GEOMETRIQUES  ");
            System.out.println("========================");
            System.out.println("1.- Llistar             ");
            System.out.println("2.- Dibuixar            ");
            System.out.println("3.- Perimetres          ");
            System.out.println("4.- Arees               ");
            System.out.println("5.- Escalar             ");
            System.out.println("6.- Moure               ");
            System.out.println("7.- Desplaçar           ");
            System.out.println("8.- Ordenar             ");
            System.out.println("9.- Eixir               ");
            System.out.println("========================");
            System.out.println("Tria una opcio: ");
            op = teclat.nextInt();teclat.nextLine();

            switch(op){
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 7:
                    break;

                case 8:
                    do{
                        System.out.println("================");
                        System.out.println("  MENU ORDENAR  ");
                        System.out.println("================");
                        System.out.println("1.- Area        ");
                        System.out.println("2.- Perimtetre  ");
                        System.out.println("3.- Posicio     ");
                        System.out.println("4.- Eixir       ");
                        System.out.println("================");
                        System.out.println("Criteri a comparar:");
                        opSubMenu = teclat.nextInt();teclat.nextLine();
                        
                        switch(opSubMenu){
                            case 1:
                                break;
                                
                            case 2:
                                break;
                                
                            case 3:
                                break;
                                
                            case 4:
                                System.out.println("Has triat eixir!");
                                seguirSubMenu = false;
                                break;
                                
                            default:
                                System.out.println("Has triat una opcio incorrecta");
                                seguirSubMenu = false;
                        }
                        
                    }while(seguirSubMenu);
                    break;
                
                case 9:
                    System.out.println("Has triat eixir!");
                    seguir = false;
                    break;
                    
                default:
                    System.out.println("No has triat una opcio correcta");
                    seguir = false;
            }
        }while(seguir);
        
    }
}
