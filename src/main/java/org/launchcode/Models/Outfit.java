package org.launchcode.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by mettenichols on 4/12/17.
 */
@Entity
public class Outfit {

    @Id
    @GeneratedValue
    @Column(name="outfit_id")
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "please provide a description ")
    private String description;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;


    @ManyToMany
    @JoinTable(name="outfit_tag", joinColumns = @JoinColumn(name="outfit_id"), inverseJoinColumns=@JoinColumn(name="tag_id"))
    private List<Tag> tagList;


    public Outfit() {}

    public Outfit(String name, String description, byte[] image, List<Tag> tagList) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.tagList = tagList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

