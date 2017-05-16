package org.launchcode.Models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mettenichols on 5/16/17.
 */
public class ClientOutfit {

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    @Size(min = 1, message = "please provide a description ")
    private String description;

    private List<String> tagList= new ArrayList<>();


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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
