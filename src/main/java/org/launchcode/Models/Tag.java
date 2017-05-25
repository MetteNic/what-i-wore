package org.launchcode.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mettenichols on 5/11/17.
 */
@Entity
public class Tag {

    @Id
    @GeneratedValue
    @Column(name="tag_id")
    private int id;

    @NotNull
    private String name;

    public Tag() {};

    public Tag (String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
