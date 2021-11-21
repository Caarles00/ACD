package com.ieseljust.ad.myDBMS;

import APAC2.Leer;
import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class databaseManager{
    
    String server;
    String port;
    String user;
    String pass;
    String dbname;

    
//    databaseManager(){
//        this.server = "localhost";
//        this.port = "3308";
//        this.user = "root";
//        this.pass = "root";
//    }

    databaseManager(String server, String port, String user, String pass, String dbname){
        this.server = server;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.dbname = dbname;
    }

    public Connection connectDatabase(){
        Connection con=null;
        try {
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

    public void showTables(){
        try {

            DatabaseMetaData dbmd = connectDatabase().getMetaData();
            
            System.out.println("========================================");
            System.out.println(String.format("%-15s %-15s %-15s", "Data Base", "Taula", "Tipus"));
            System.out.println("========================================");
                     
            ResultSet rsmd = dbmd.getTables(this.dbname, null, null, null);
            while(rsmd.next()){
                System.out.println(String.format("%-15s %-15s %-15s", rsmd.getString(1), rsmd.getString(3), rsmd.getString(4)));
            }
            System.out.println("========================================");
            rsmd.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertIntoTable(String table){
        try {
            ArrayList camps = new ArrayList();
            ArrayList tipus = new ArrayList();
            ArrayList valors = new ArrayList();
            ArrayList nulls = new ArrayList();
            ArrayList autoincrement = new ArrayList();
            
            DatabaseMetaData dbmd = connectDatabase().getMetaData();
            ResultSet columnes = dbmd.getColumns(this.dbname, null, table, null);
            
            while(columnes.next())
            { 
                camps.add(columnes.getString(4));
                tipus.add(columnes.getString(6));
                nulls.add(columnes.getString(18));
                autoincrement.add(columnes.getString(23));    
            }
            
            String valores = "";
            String campos = "";
            
            for (int i = 0; i < camps.size(); i++)
            {
                valors.add("?");
            }
            for (Object element : valors) {
                valores += element + ", ";
            }
            for (Object element : camps) {
                campos += element + ", ";
            }
            
            String sql = "INSERT INTO" + table + " (" + clear(campos) + ")" + " VALUES " + "(" + clear(valores) + ")";
            PreparedStatement pst = connectDatabase().prepareStatement(sql);
            
            for (int i = 0; i < camps.size(); i++) {
                if (!"NULL".equals(nulls.get(i - 1))) {
                    if ("YES".equals(autoincrement.get(i - 1))) {
                        System.out.println("El camp " + camps.get(i - 1) + " es autoincrementable");
                    }
                    else{
                        if ("INT".equals(tipus.get(i - 1))) {
                            String tipo_valor = Leer.leerTexto("Introdix un valor de tipo " + tipus.get(i - 1) + " para el campo " + camps.get(i - 1) + " :");
                            System.out.println("");
                            System.out.println("----------");
                            pst.setInt(i, Integer.parseInt(tipo_valor));
                        }
                        
                        if("DATE".equals(tipus.get(i - 1))){
                                String data = Leer.leerTexto("Introduix un valor de tipo " + tipus.get(i - 1) + " per al camp " + camps.get(i - 1)+ " :");
                                System.out.println("");
                                System.out.println("----------");
                                pst.setDate(i, java.sql.Date.valueOf(data));
                        }
                        else{
                            String tipo_valor2 = Leer.leerTexto("Introduix un valor de tipo " + tipus.get(i - 1) + " paer al camp " + camps.get(i - 1)+ " :");
                            System.out.println("");
                            System.out.println("----------");
                            pst.setString(i, tipo_valor2);
                        }
                    }
                }
                else{
                    if ("YES".equals(autoincrement.get(i - 1))) {
                        System.out.println("El camp " + camps.get(i - 1) + " es autoincrementable");
                    }
                    else{
                        String resposta = Leer.leerTexto("Vols introduir algo en la columna " + camps.get(i - 1) + " de tipo " + tipus.get(i - 1) + "?" + "\nEscriu Y|N: ");
                        
                        while(resposta != "Y" || resposta != "N"){
                            resposta = Leer.leerTexto("Vols introduir algo en la columna " + camps.get(i - 1) + " de tipo " + tipus.get(i - 1) + "?" + "\nEscriu Y|N: ");
                        }
                        
                        if ("Y".equals(resposta)) {
                            if ("YES".equals(autoincrement.get(i - 1))) {
                                System.out.println("El camp " + camps.get(i - 1) + " es autoincrementable");
                            }
                            else{
                                if ("INT".equals(tipus.get(i - 1))) {
                                String tipo_valor = Leer.leerTexto("Introdix un valor de tipo " + tipus.get(i - 1) + " para el campo " + camps.get(i - 1) + " :");
                                System.out.println("");
                                System.out.println("----------");
                                pst.setInt(i, Integer.parseInt(tipo_valor));
                            }

                                if("DATE".equals(tipus.get(i - 1))){
                                        String data = Leer.leerTexto("Introduix un valor de tipo " + tipus.get(i - 1) + " per al camp " + camps.get(i - 1)+ " :");
                                        System.out.println("");
                                        System.out.println("----------");
                                        pst.setDate(i, java.sql.Date.valueOf(data));
                                }
                                else{
                                    String tipo_valor2 = Leer.leerTexto("Introduix un valor de tipo " + tipus.get(i - 1) + " paer al camp " + camps.get(i - 1)+ " :");
                                    System.out.println("");
                                    System.out.println("----------");
                                    pst.setString(i, tipo_valor2);
                                }
                            }
                        }
                        else if ("N".equals(resposta)){
                            if ("INT".equals(tipus.get(i - 1))) {
                                pst.setInt(i, Integer.parseInt(""));
                            }
                            else if ("DATE".equals(tipus.get(i - 1))){
                                pst.setDate(i, java.sql.Date.valueOf(""));
                            }
                            else{
                                pst.setString(i, "");
                            }
                        }
                    }
                }
            }
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showDescTable(String table){
        try {            
            DatabaseMetaData dbmd = connectDatabase().getMetaData();
            ResultSet rspk = dbmd.getPrimaryKeys(this.dbname, null, table);
            ArrayList<String> pks = new ArrayList<>();
            
            while(rspk.next()){
                pks.add(rspk.getString(4));
            }
            rspk.close();
            
            
            ResultSet rsfk = dbmd.getImportedKeys(this.dbname, null, table);
            ArrayList<String> fks = new ArrayList<String>();
            ArrayList<String> fksExt = new ArrayList<String>();
            
            while(rsfk.next()){
                fks.add(rsfk.getString(8));
                fksExt.add(rsfk.getString(3));
            }
            rsfk.close();
            
            ResultSet columnes = dbmd.getColumns("BDJocs", null, table, null);
            System.out.println("===================");
            System.out.println("       TAULA       ");
            System.out.println("===================");
            System.out.println(String.format("%-25s %-15s %-15s", "Atribut/Claus", "Tipus", "Pot ser nul?"));
            System.out.println("===================");
            
            while(columnes.next()){
                String columnName = columnes.getString(4);
                if (pks.contains(columnName)) {
                    columnName = columnName + "(PK)";
                }
                
                if (fks.contains(columnName)) {
                    columnName = columnName + "(FK) -->" + fksExt.get(fks.indexOf(columnName));
                }
                
                String tipus = columnes.getString(6);
                String nullable = columnes.getString(18);
                
                System.out.println(String.format("%-25s %-15s %-15s", columnName, tipus, nullable));
            }


            
        } catch (SQLException ex) {
            Logger.getLogger(databaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void executeSelect(String query){
        
        ResultSet rs = null;
        System.out.println(query);
        
        try
        {

            rs = connectDatabase().createStatement().executeQuery(query);

            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT);
            System.out.println("======================");
            System.out.println("Contengut de la taula");
            System.out.println("======================");
            
            ResultSetMetaData rsmdQuery = rs.getMetaData();
            for (int i = 1; i <= rsmdQuery.getColumnCount(); i++)
            System.out.print(String.format("%-25.25s",rsmdQuery.getColumnName(i)));

            System.out.println("");
            System.out.println(ConsoleColors.RESET);

            while (rs.next()) 
            {
                
                for (int i = 1; i <= rsmdQuery.getColumnCount(); i++)
                {  
                    
                    System.out.print(String.format("%-25.25s ",rs.getString(i)));
                    
                }
                System.out.println();    
                
            }

        }
        catch(Exception e)
        {
            System.out.println("Error, format de select incorrecte \n"+e);
        }
        
    }

    public void startShell(){       
        Scanner keyboard = new Scanner(System.in);
        String command;
        do {

            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT+"# ("+this.user+") on "+this.server+":"+this.port + "[" + this.dbname + "]> "+ConsoleColors.RESET);
            command = keyboard.nextLine();
                        
            switch (command){
                case "help":
                    System.out.println("Ordre                               Descripcio");    
                    System.out.println("======================================================================================");
                    System.out.println("show tables o sh tables             Mostra les taules de la base de dades");
                    System.out.println("info                                Mostra informacio sobre el SGBD i la connexio");
                    System.out.println("insert Nom_de_la_Taula              Pregunta al usuari camp per camp de la taula, i inserix un registre a la base de dades");
                    System.out.println("describe Nom_de_la_Taula            Mostra una descripcio dels camps de la taula indicada");
                    System.out.println("select *                            Executara una consulta select qualsevol en la BD");
                    System.out.println("quit                                Torna al mode general");
                    break;
                case "sh tables":
                case "show tables":
                    this.showTables();
                    break;
                
                case "quit":
                    break;
                default:
                    String[] subcommand=command.split(" ");
                    switch (subcommand[0]){
                        case "insert":
                            this.insertIntoTable(subcommand[1]);
                            break;
                        
                        case "describe":
                            this.showDescTable(subcommand[1]);
                            break;
                            
                        case "select":
                            this.executeSelect(command);
                            break;
                            
                        default:
                            System.out.println(ConsoleColors.RED+"Unknown option"+ConsoleColors.RESET);
                            break;
                    }
            }            
        } while(!command.equals("quit"));        
    }
    
    // Mètode per a separar per comes
    private static String clear(String datosArray){
        datosArray = datosArray.trim();
        if (datosArray != null && datosArray.length() > 0 && datosArray.charAt(datosArray.length() - 1) == ',') {           
          datosArray = datosArray.substring(0, datosArray.length() - 1);              
        }
        return datosArray;
   }
    
}