
package Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author carles
 */
@Entity
@Table(name="pokemon")
public class pokemon {
    
    //Atributs
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long IDpoke;
    
    @Column
    private String pokename;
    
    @Column
    private String type;
    
//    @OneToOne(cascade=CascadeType.ALL)
//    private pokemon luchar;
    
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="IDcoach")
    private coach elcoach;
    
    
    @ManyToMany(cascade=CascadeType.PERSIST,
                fetch=FetchType.LAZY
    )
    @JoinTable(name="poke-ability",
              joinColumns = {@JoinColumn(name="IDpoke")},
              inverseJoinColumns = {@JoinColumn(name="IDability")}
    )
    private Set<abilities> lesAbilities;
    
    //Constructros
    public pokemon(String pokename, String type, coach elcoach /*pokemon luchar*/) {
        this.pokename = pokename;
        this.type = type;
        this.elcoach = elcoach;
        //this.luchar = luchar;
        this.lesAbilities = new HashSet<>();
    }

    public pokemon(String pokename, String type) {
        this.pokename = pokename;
        this.type = type;
    }
    

    public pokemon() {
        this.lesAbilities = new HashSet<>();
    }
    
    //Getters i setters

    public Long getIDpoke() {
        return IDpoke;
    }

    public void setIDpoke(Long IDpoke) {
        this.IDpoke = IDpoke;
    }

    public String getPokename() {
        return pokename;
    }

    public void setPokename(String pokename) {
        this.pokename = pokename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public coach getElcoach() {
        return elcoach;
    }

    public void setElcoach(coach elcoach) {
        this.elcoach = elcoach;
    }

    public Set<abilities> getLesAbilities() {
        return lesAbilities;
    }

    public void setLesAbilities(Set<abilities> lesAbilities) {
        this.lesAbilities = lesAbilities;
    }

//    public pokemon getLuchar() {
//        return luchar;
//    }
//
//    public void setLuchar(pokemon luchar) {
//        this.luchar = luchar;
//    }
    
    
    
    
    //To String
    @Override
    public String toString() {
        String text = String.format("%-15s %-15s %-15s", IDpoke, pokename, type);
        return text;
    } 
    
    public void mostrarAbilities(){
        if (lesAbilities.isEmpty()) {
            System.out.println("Aquest pokemon no te cap habilitat");
        } else {
            for (abilities a : lesAbilities) {
                System.out.println(a);
            }
        }
    }
    
    public void addAbility(abilities a){
        if (!this.lesAbilities.contains(a)) {
            lesAbilities.add(a);
            a.addPokemon(this);
        }
    }
}