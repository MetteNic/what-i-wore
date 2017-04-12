package org.launchcode.Controllers;

import org.launchcode.Models.Data.OutfitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}

