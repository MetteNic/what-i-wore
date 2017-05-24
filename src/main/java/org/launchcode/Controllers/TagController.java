package org.launchcode.Controllers;

import org.launchcode.Models.Data.OutfitDao;
import org.launchcode.Models.Data.TagDao;
import org.launchcode.Models.Outfit;
import org.launchcode.Models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mettenichols on 4/29/17.
 */
@Controller
@RequestMapping("tags")
public class TagController {
    @Autowired
    TagDao tagDao;

    @Autowired
    OutfitDao outfitDao;



    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("tags", tagDao.findAll());
        model.addAttribute("title", "Tags");

        return "tags/index";
    }

    @RequestMapping (value="/{id}", method = RequestMethod.GET)
    public String displaySearchResult(@PathVariable final int id, Model model) {
         String searchTerm = tagDao.findById(id).getName();

         model.addAttribute("outfits", outfitDao.findByTagListName(searchTerm));
         model.addAttribute("title", "Outfits");

         return"tags/results";


    }


}
