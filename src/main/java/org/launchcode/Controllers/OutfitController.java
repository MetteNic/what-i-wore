package org.launchcode.Controllers;

import org.launchcode.Models.Data.OutfitDao;
import org.launchcode.Models.Outfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by mettenichols on 4/12/17.
 */
@Controller
public class OutfitController {

    @Autowired
    OutfitDao outfitDao;

    @RequestMapping(value ="add", method =RequestMethod.GET)
    public String displayAddOutfitForm(Model model){
        model.addAttribute("title", "Add Outfit");
        model.addAttribute(new Outfit());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Outfit newOutfit,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Outfit");
            return "add";
        }

        outfitDao.save(newOutfit);
        model.addAttribute("title", "Browse Outfits");
        model.addAttribute("outfits", outfitDao.findAll());
        return "browse";
    }
}

