/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author carles
 */
@Entity
@Table(name="coach")
public class coach {
    
    //Atributs
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long IDcoach;
    
    @Column
    private String coachname;
    
    @Column
    private int num_of_poke;
    
    @OneToMany(mappedBy="elcoach",cascade=CascadeType.PERSIST)
    private Set<pokemon> elsPokemon;
    
    //Getters i setters
    public Long getIDcoach() {
        return IDcoach;
    }

    public void setIDcoach(Long IDcoach) {
        this.IDcoach = IDcoach;
    }

    public String getCoachname() {
        return coachname;
    }

    public void setCoachname(String coachname) {
        this.coachname = coachname;
    }

    public int getNum_of_poke() {
        return num_of_poke;
    }

    public void setNum_of_poke(int num_of_poke) {
        this.num_of_poke = num_of_poke;
    }

    public Set<pokemon> getElsPokemon() {
        return elsPokemon;
    }

    public void setElsPokemon(Set<pokemon> elsPokemon) {
        this.elsPokemon = elsPokemon;
    }
    
    //Constructors
    public coach(String coachname, int num_of_poke) {
        this.coachname = coachname;
        this.num_of_poke = num_of_poke;
        this.elsPokemon = new HashSet<>();
    }

    public coach() {
        this.elsPokemon = new HashSet<>();
    }
    
    //To String
    @Override
    public String toString() {
        String text = String.format("%-15s %-15s %-15s", IDcoach, coachname, num_of_poke);
        return text;
    }
    
    
}
