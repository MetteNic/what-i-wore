package org.launchcode.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mettenichols on 4/25/17.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min =3, max = 25)
    private String name;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Outfit> outfits = new ArrayList<>();

    //Constructors
    public Location(){};

    public Location(String name){

        this.name = name;


    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Outfit> getOutfits() {
        return outfits;
    }


}
