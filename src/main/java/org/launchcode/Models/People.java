package org.launchcode.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mettenichols on 4/29/17.
 */
@Entity
public class People {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min =3, max = 25)
    private String name;

    @OneToMany
    @JoinColumn(name = "people_id")
    private List<Outfit> outfits = new ArrayList<>();

    //Constructors
    public People(){};

    public People(String name){

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
