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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author alumne
 */
@Entity
@Table(name="abilities")
public class abilities {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private Long IDability;
    
    @Column
    private String abilityname;
    
    @Column 
    private String type_ability;
    
    @ManyToMany(cascade=CascadeType.PERSIST,
                fetch=FetchType.LAZY,
                mappedBy="lesAbilities"
    )
    private Set<pokemon> elsPokemon;

    public abilities(String abliliyname, String type_ability) {
        this.abilityname = abliliyname;
        this.type_ability = type_ability;
        this.elsPokemon = new HashSet<>();
    }

    public abilities() {
        this.elsPokemon = new HashSet<>();
    }

    public Long getIDability() {
        return IDability;
    }

    public void setIDability(Long IDability) {
        this.IDability = IDability;
    }

    public String getAbliliyname() {
        return abilityname;
    }

    public void setAbliliyname(String abliliyname) {
        this.abilityname = abliliyname;
    }

    public String getType_ability() {
        return type_ability;
    }

    public void setType_ability(String type_ability) {
        this.type_ability = type_ability;
    }

    public Set<pokemon> getElsPokemon() {
        return elsPokemon;
    }

    public void setElsPokemon(Set<pokemon> elsPokemon) {
        this.elsPokemon = elsPokemon;
    }

    @Override
    public String toString() {
        String text = String.format("%-15s %-15s %-15s", IDability, type_ability, abilityname);
        return text;
    }
    
    public String mostrarPokemons(){
        String res = ";";
        for (pokemon p : elsPokemon) {
            res += p.getPokename();
        }
        return res;
    }
    
    public void addPokemon(pokemon p){
        if (!this.elsPokemon.contains(p)) {
            elsPokemon.add(p);
            p.addAbility(this);
        }
    }
    
}
