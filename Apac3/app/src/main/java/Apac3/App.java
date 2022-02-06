/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Apac3;

import java.util.Scanner;
import org.hibernate.Session;
import Model.coach;
import Model.pokemon;
import Model.abilities;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.hibernate.query.Query;

public class App {
    public static void main(String[] args) {
        disableLogging();
        startShell();
    }
    
    private static void disableLogging(){
        LogManager logManager = LogManager.getLogManager();
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE); //could be Level.OFF
    }
    
    public static void showTablesPokemon(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        Query q = laSessio.createQuery("FROM pokemon");
        List<pokemon> elsPokemon = q.list();
        
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Llistat dels Pokemon" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + String.format("%-15s %-15s %-15s", "Id", "Nom", "Tipo" + ConsoleColors.RESET));
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        for (pokemon p : elsPokemon) {
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + p + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        laSessio.getTransaction().commit();
    }
    
    public static void showTablesPokemonC(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        Query q = laSessio.createQuery("FROM pokemon");
        List<pokemon> elsPokemon = q.list();
        
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Llistat dels Pokemon" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + String.format("%-15s %-15s %-15s", "Id", "Nom", "Tipo" + ConsoleColors.RESET));
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        for (pokemon p : elsPokemon) {
            System.out.println(p);
            System.out.println("Les habilitats d'aquest pokemon son: ");
            p.mostrarAbilities();
            System.out.println("");
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        laSessio.getTransaction().commit();
    }
    
    public static void showTableCoach(){
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        Query q = laSessio.createQuery("FROM coach");
        List<coach> elsCoach = q.list();
        
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Llistat dels entrenadors" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + String.format("%-15s %-15s %-15s", "Id", "Nom", "Numero de pokemons" + ConsoleColors.RESET));
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        for (coach c : elsCoach) {
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + c + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        laSessio.getTransaction().commit();
    }
    
    public static void showTableCoachC(){
        
    }
    
    public static void showTableAbilitiesC(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        Query q = laSessio.createQuery("FROM abilities");
        List<abilities> lesAbilities = q.list();
        
        showTableAbilities();
        
        for (abilities a : lesAbilities) {
            System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + a + ConsoleColors.RESET);
            a.mostrarPokemons();
        }
        
        laSessio.getTransaction().commit();
    }
    
    public static void showTableAbilities(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        Query q = laSessio.createQuery("FROM abilities");
        List<abilities> lesAbilities = q.list();
        
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Llistat de les habilitats" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-15s %-15s %-15s", "Id", "Nom", "Tipo" + ConsoleColors.RESET));
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        for (abilities a : lesAbilities) {
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + a + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "---------------------------------------------------------" + ConsoleColors.RESET);
        
        
        laSessio.getTransaction().commit();
    }
    
    public static void addPokemon(){

        String nom = Leer.leerTexto("Digues el nom del pokemon: ");
        String type = Leer.leerTexto("Digues el tipo de pokemon: ");
        pokemon p = new pokemon(nom, type);
        
        
        String pokemon_amb_entrenador = Leer.leerTexto("Aquest pokemon té algun entrenador? (s/n): ");
        while(!pokemon_amb_entrenador.equalsIgnoreCase("s") && !pokemon_amb_entrenador.equalsIgnoreCase("n")){
            pokemon_amb_entrenador = Leer.leerTexto("Error. Aquest pokemon té algun entrenador? (s/n): ");
        }
        
        if (pokemon_amb_entrenador.equalsIgnoreCase("n")){
            Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();
            
            p = new pokemon(nom, type);
            laSessio.save(p);
            System.out.println("Pokemon inserit correctament");
            
            laSessio.getTransaction().commit();
        
            
        }else{
            showTableCoach();
            String add_to_coach = Leer.leerTexto("Digues el ID del entrenador al que vols afegir el pokemon: ");
            
            Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
            laSessio.beginTransaction();
            
            coach c = laSessio.get(coach.class, Long.parseLong(add_to_coach));
            p.setElcoach(c);
            laSessio.save(p);
            System.out.println("Pokemon inserit correctament");
            
            laSessio.getTransaction().commit();
        }
    }
    
    public static void addCoach(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        String nom = Leer.leerTexto("Digues el nom del entrenador: ");
        int num = Leer.leerEntero("Digues el numero de pokemons que te: ");
        
        coach c = new coach(nom, num);
        laSessio.save(c);
        
        laSessio.getTransaction().commit();
    }
    
    public static void addAbiliy(){
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        String nom = Leer.leerTexto("Digues el nom de l'habilitat: ");
        String type = Leer.leerTexto("Digues el tipo d'habilitat: ");
        
        abilities a = new abilities(nom, type);
        laSessio.save(a);
        
        laSessio.getTransaction().commit();
    }
    
    public static void updatePokemon(){
        showTablesPokemon();
        
        String idUpdate = Leer.leerTexto("Digues la ID del pokemon: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        pokemon p = laSessio.get(pokemon.class, Long.parseLong(idUpdate));
        
        
        
        String NewNom = Leer.leerTexto("Nom <-> " + p.getPokename() + ": ");
        if (NewNom.isEmpty()) {
            
        }else{
            p.setPokename(NewNom);
        }
        
        String NewType = Leer.leerTexto("Tipo <-> " + p.getType() + ": ");
        if (NewType.isEmpty()) {
            
        }else{
            p.setType(NewType);
        }
        System.out.println("Pokemon actualitzat");
        laSessio.update(p);
        
        laSessio.getTransaction().commit();
        
    }
    
    public static void updateCoach(){
        showTableCoach();
        
        String idUpdate = Leer.leerTexto("Digues la ID del pokemon: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        coach c = laSessio.get(coach.class, Long.parseLong(idUpdate));
        
        String NewNom = Leer.leerTexto("Nom <-> " + c.getCoachname()+ ": ");
        if (NewNom.isEmpty()) {
            
        }else{
            c.setCoachname(NewNom);
        }
        
        int NewNum = Leer.leerEntero("Numero de pokemons <-> " + c.getNum_of_poke()+ ": ");
        c.setNum_of_poke(NewNum);
        System.out.println("Entrenador actualitzat");
        laSessio.update(c);
        
        laSessio.getTransaction().commit();
        
    }
    
    public static void updateAbilities(){
        showTableAbilities();
        
        String idUpdate = Leer.leerTexto("Digues l'ID de l'abilitat: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        abilities a = laSessio.get(abilities.class, idUpdate);
        
        String NewNom = Leer.leerTexto("Nom <-> " + a.getAbliliyname()+ ": ");
        if (NewNom.isEmpty()) {
            
        }else{
            a.setAbliliyname(NewNom);
        }
        
        String NewType = Leer.leerTexto("Tipo <-> " + a.getType_ability()+ ": ");
        if (NewType.isEmpty()) {
            
        }else{
            a.setType_ability(NewType);
        }
        System.out.println("Habilitat actualitzada");
        laSessio.update(a);
        
        laSessio.getTransaction().commit();
        
    }
    
    public static void deletePokemon(){
        showTablesPokemon();
        String id = Leer.leerTexto("Digues l'ID del pokemon que vols borrar: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        pokemon p = laSessio.get(pokemon.class, Long.parseLong(id));
        laSessio.delete(p);
        laSessio.getTransaction().commit();
        
    }
    
    public static void deleteCoach(){
        showTableCoach();
        
        String id = Leer.leerTexto("Digues l'ID del entrenador que vols borrar: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        coach c = laSessio.get(coach.class, Long.parseLong(id));
        laSessio.delete(c);
        laSessio.getTransaction().commit();
    }
    
    public static void deleteAbilities(){
        showTableAbilities();
        
        String id = Leer.leerTexto("Digues l'ID de l'habilitat que vols borrar: ");
        
        Session laSessio = HibernateUtil.getSessionFactory().getCurrentSession();
        laSessio.beginTransaction();
        
        abilities a = laSessio.get(abilities.class, Long.parseLong(id));
        laSessio.delete(a);
        laSessio.getTransaction().commit();
    }
    
    public static void startShell(){
        String command;
        do{
            System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "BDPokemon:> " + ConsoleColors.RESET);
            Scanner keyboard = new Scanner(System.in);
            command = keyboard.nextLine();
            String[] subcommand = command.split(" ");
            switch(subcommand[0]){
                case "help":
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "--------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "------               APAC 3. Ajuda bàsica              -------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "--------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "ordre", "descripció" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "----", "------------------------------------" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "help", "Mostra l'ajuda"));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "show [-c] table", "Mostra el contingut d'una taula" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "add table", "Afegeix un nou registre a la taula" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "update table", "Actualitza una taula" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "delete table", "Esborra un registre de la taula" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "clear", "Netetja la pantalla" + ConsoleColors.RESET));
                    System.out.println(String.format(ConsoleColors.BLUE_BOLD_BRIGHT + "%-25s %-25s", "quit", "Acaba el programa" + ConsoleColors.RESET));
                    System.out.print("\n");
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "--------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Les taules sobre les que podem fer manteniment són:" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "--------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Pokemon" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Coach" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Abilities" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "--------------------------------------------------------------" + ConsoleColors.RESET);
                    break;
                 
                case "quit":
                    System.out.println("Fi del programa");
                    break;
                
                case "show":
                    if (subcommand.length > 2 && subcommand[1].equalsIgnoreCase("-c")) {
                        if (subcommand[2].equalsIgnoreCase("Pokemon")) {
                            showTablesPokemonC();
                        }
                        else if (subcommand[2].equalsIgnoreCase("Coach")) {
                            showTableCoachC();
                        }
                        else if (subcommand[2].equalsIgnoreCase("Abilities")) {
                            showTableAbilitiesC();
                        }
                        else{
                            System.out.println("Opcio incorrecta");
                        }
                    } else{
                        if (subcommand[1].equalsIgnoreCase("Pokemon")) {
                            showTablesPokemon();
                        }else if(subcommand[1].equalsIgnoreCase("Coach")){
                            showTableCoach();
                        } else if (subcommand[1].equalsIgnoreCase("Abilities")) {
                            showTableAbilities();
                        }
                    }
                    break;
                    
                case "add":
                    if (subcommand[1].equalsIgnoreCase("Pokemon")) {
                        addPokemon();
                    } else if (subcommand[1].equalsIgnoreCase("Coach")) {
                        addCoach();
                    } else if (subcommand[1].equalsIgnoreCase("Abilities")){
                        addAbiliy();
                    }
                    break;

                case "update":
                    if (subcommand[1].equalsIgnoreCase("Pokemon")) {
                        updatePokemon();
                    } else if (subcommand[1].equalsIgnoreCase("Coach")) {
                        updateCoach();
                    } else if (subcommand[1].equalsIgnoreCase("Abilities")){
                        updateAbilities();
                    }
                    break;
                    
                case "delete":
                    if (subcommand[1].equalsIgnoreCase("Pokemon")) {
                        deletePokemon();
                    } else if (subcommand[1].equalsIgnoreCase("Coach")) {
                        deleteCoach();
                    } else if (subcommand[1].equalsIgnoreCase("Abilities")){
                        deleteAbilities();
                    }
                    break;
                
                case "clear":
                    System.out.flush();
                    break;
                    
                default:
                    System.out.println("Opció desconeguda");
                    break;
            }
        }while(!command.equals("quit"));
    }

    Object getGreeting() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}