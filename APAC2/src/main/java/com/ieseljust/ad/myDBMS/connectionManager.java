package com.ieseljust.ad.myDBMS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class connectionManager{
    
    String server;
    String port;
    String user;
    String pass;
    
//    connectionManager(){
//        this.server = "localhost";
//        this.port = "3308";
//        this.user = "alumne";
//        this.pass = "root";   
//    }

    connectionManager(String server, String port, String user, String pass){
        this.server = server;
        this.pass = pass;
        this.port = port;
        this.user = user;
    }

    public Connection connectDBMS(){     
        try {
            Connection con=null;
            
            // Ens connectem
            String connectionUrl = "jdbc:mysql://" + this.server + ":" + this.port +
                    "?useUnicode=true&characterEncoding=UTF-8"+
                    "&user=" + this.user +
                    "&password=" + this.pass +
                    "&allowMultiQueries=true";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl);
            
            return con;
            
            
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void showInfo(){
        try {
            DatabaseMetaData dbmd = connectDBMS().getMetaData();
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "=======================");
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "  Informació del SGBD  ");
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "=======================");
            System.out.println("Nom: " + dbmd.getDatabaseProductName());
            System.out.println("Driver: " + dbmd.getDriverName());
            System.out.println("URL: " + dbmd.getURL());
            System.out.println("Usuari: " + dbmd.getUserName());
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "======================");
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDatabases(){
        try {
            DatabaseMetaData dbmt = connectDBMS().getMetaData();
            System.out.println("==================");
            System.out.println("  BASES DE DATOS  ");
            System.out.println("==================");

            ResultSet rs = dbmt.getCatalogs();
            
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
            System.out.println("==================");

            
        } catch (SQLException ex) {
            Logger.getLogger(connectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void importScript(String ExScript){
        File script = new File(ExScript);
        System.out.println("Executant script "+ script.getName());

        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(script));  
        } catch(FileNotFoundException e)
        { 
            System.out.println("Error: Script no existent");  
        }
        
        String line = null;
        StringBuilder sb= new StringBuilder();

        String breakLine=System.getProperty("line.separator");

        try
        {
            while ((line=br.readLine())!=null) 
            {
                
                sb.append(line);
                sb.append(breakLine);
                
            }
        } catch (IOException e)
        {
            System.out.println ("ERROR");
        }

        String query = sb.toString();

        System.out.println("Executant consulta: \n" + query);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error en carregar el driver");
        }

        try{
        
            Statement st= connectDBMS().createStatement();
            int result=st.executeUpdate(query);

            System.out.println("Script Executat amb éxit. Eixida: "+
            result);

            st.close();
            connectDBMS().close();

        } catch (SQLException e){
        System.out.println("Error del script " + e);
        }
    }

    public void startShell(){

        Scanner keyboard = new Scanner(System.in);
        String command;

        do {

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"# ("+this.user+") on "+this.server+":"+this.port+"> "+ConsoleColors.RESET);
            command = keyboard.nextLine();

                        
            switch (command){
                case "help":
                    System.out.println("Ordre                               Descripcio");    
                    System.out.println("====================================================================================");
                    System.out.println("show databases o sh db              Mostra una llista amb les diferents bases de dades del sistema");
                    System.out.println("info                                Muestra informacion del Servidor");
                    System.out.println("import Nom_del_script               Permetra executar un script sql indicant la ubicació del fitxer");
                    System.out.println("use Nom_de_la_BD                    Canvia al mode de connexio a la base de dades");
                    System.out.println("quit                                Ix de l'aplicacio");
                    break;
                    
                case "sh db":
                case "show databases":
                    this.showDatabases();
                    break;
                
                case "info":
                    this.showInfo();
                    break;

                case "quit":
                    break;
                default:

                    String[] subcommand=command.split(" ");
                    switch (subcommand[0]){
                        case "use":
                            databaseManager dbm = new databaseManager(server, port, user, pass, subcommand[1]);
                            dbm.startShell();
                            break;
                            
                        case "import":
                            this.importScript(subcommand[1]);
                            break;
                           
                        default:
                            System.out.println(ConsoleColors.RED+"Unknown option"+ConsoleColors.RESET);
                            break;
                    }

            }           
        } while(!command.equals("quit"));       
        }
}