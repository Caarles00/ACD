
package pkg2020_ad_p1_warship;

import Utils.ConsoleColors;
import Utils.Leer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joange
 */
public class WarShip {

    /**
     * @param args the command line arguments
     */
    public static boolean end = false;
    
    static int MAX_JUGADAS = 100;

    private Random r;
    private Board board;
    private WarShip ws;

    public WarShip() {
        r = new Random(System.currentTimeMillis());
        board = new Board();
        board.initBoats();
    }

    private void autoPlay() {

        board.paint();

        // Vamos a realizar 50 jugadas aleatorias ...
        for (int i = 1; i <= MAX_JUGADAS; i++) {
            System.out.println(ConsoleColors.GREEN_BRIGHT+"JUGADA: " + i);

            int fila, columna;
            do {
                fila = r.nextInt(Board.BOARD_DIM);
                columna = r.nextInt(Board.BOARD_DIM);
            } while (board.fired(fila, columna));

            if (board.shot(fila, columna) != Cell.CELL_WATER) {
                board.paint();
            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");
            }

            if (board.getEnd_Game()) {
                System.out.printf("Joc acabat amb %2d jugades\n", i);
                break;
            }
        }
    }

    private void play() {
        int num_jugadas = 0;
        boolean rendit = false;

        String jugada;
        int fila = -1, columna = -1;
        do {
            do {
                jugada = Leer.leerTexto("Dime la jugada en dos letras A3, B5... de A0 a J9: ").toUpperCase();
                if (jugada.equalsIgnoreCase("00")) {
                    System.out.println("Jugador rendit");
                    rendit = true;
                    break;
                }
                if (jugada.length() == 0 || jugada.length() > 2) {
                    System.out.println("Format incorrecte.");
                    continue;
                }

                fila = jugada.charAt(0) - 'A';
                columna = jugada.charAt(1) - '0';

            } while (board.fired(fila, columna));

            // acaba el joc
            if (rendit) {
                break;
            }

            num_jugadas++;

            if (board.shot(fila, columna) != Cell.CELL_WATER) {
                board.paintGame();
            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");
            }

            if (board.getEnd_Game()) {
                System.out.printf("Joc acabat amb %2d jugades\n", num_jugadas);
                break;
            }

        } while (num_jugadas < MAX_JUGADAS);

    }

    public static void main(String[] args) {
        
        // TODO code application logic here
        WarShip ws = new WarShip();
        guardarFitxerProperties();
        
        int opcio = 0;
        do {
            System.out.println(ConsoleColors.GREEN+"--    Escollir   --");
            System.out.println(ConsoleColors.GREEN+"1. Joc autom??tic...");
            System.out.println(ConsoleColors.GREEN+"2. Joc manual......");
            opcio = Leer.leerEntero(ConsoleColors.CYAN+"Indica el tipus de joc que vols: "+ConsoleColors.RESET);
        } while (opcio < 1 || opcio > 2);

        switch (opcio) {
            case 1:
                ws.autoPlay();
                break;
            case 2:
                ws.play();
                break;
        }

    }
    
    //Apartat 1
    public static void guardarFitxerProperties(){
        Properties configuracion = new Properties();
        configuracion.setProperty("board_tam", String.valueOf(Board.BOARD_DIM));
        configuracion.setProperty("num_boats", String.valueOf(Board.BOARD_BOATS_COUNT));
        configuracion.setProperty("max_jugador", String.valueOf(MAX_JUGADAS));
        try {
            configuracion.store(new FileOutputStream("warship.properties"), "Fichero de configuracion: ");
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    
    public static void cargarFitxerProperties(){
        try {
            Properties configuracion = new Properties();
            
            configuracion.load(new FileInputStream("warship.properties"));
            Board.BOARD_DIM = Integer.parseInt(configuracion.getProperty("board_tam"));
            Board.BOARD_BOATS_COUNT = Integer.parseInt(configuracion.getProperty("num_boats"));
            MAX_JUGADAS = Integer.parseInt(configuracion.getProperty("max_jugadas"));
        } catch (IOException ex) {
            Logger.getLogger(WarShip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
