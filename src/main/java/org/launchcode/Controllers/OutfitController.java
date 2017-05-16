package org.launchcode.Controllers;

import org.launchcode.Models.*;
import org.launchcode.Models.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mettenichols on 4/12/17.
 */
@Controller
public class OutfitController {

    @Autowired
    TagDao tagDao;

    @Autowired
    OutfitDao outfitDao;

    static List<Tag> tagObjectList = new ArrayList<Tag>();


    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddOutfitForm(Model model) {
        model.addAttribute("title", "Add Outfit");
        // model.addAttribute(new Outfit());
         model.addAttribute("tags", tagDao.findAll());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddOutfitForm(Model model, @RequestParam String name, @RequestParam String description,
                                       @RequestParam ArrayList<String> tagList) {

        for (String tag : tagList) {
            if (tagDao.findByName(tag) != null) {
                Tag newTag = (tagDao.findByName(name));
                tagObjectList.add(newTag);

            } else {
                Tag newTag = new Tag(tag);
                tagDao.save(newTag);
                tagObjectList.add(newTag);
            }


        }

        ClientOutfit outfit = new ClientOutfit();
        outfit.setName(name);
        outfit.setDescription(description);
        outfit.setTagList(tagList);



        Outfit persistentOutfit = new Outfit();
        persistentOutfit.setName(name);
        persistentOutfit.setDescription(description);
        persistentOutfit.setTagList(tagObjectList);
        outfitDao.save(persistentOutfit);



        model.addAttribute("outfit", outfit);
        model.addAttribute("title", "outfit added!" );

        return "browse";
    }
}

