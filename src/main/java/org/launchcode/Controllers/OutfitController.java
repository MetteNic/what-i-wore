package org.launchcode.Controllers;

import org.launchcode.Models.Data.LocationDao;
import org.launchcode.Models.Data.OccasionDao;
import org.launchcode.Models.Data.OutfitDao;
import org.launchcode.Models.Data.PeopleDao;
import org.launchcode.Models.Location;
import org.launchcode.Models.Occasion;
import org.launchcode.Models.Outfit;
import org.launchcode.Models.People;
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

    @Autowired
    LocationDao locationDao;

    @Autowired
    OccasionDao occasionDao;

    @Autowired
    PeopleDao peopleDao;


    @RequestMapping(value ="add", method =RequestMethod.GET)
    public String displayAddOutfitForm(Model model){
        model.addAttribute("title", "Add Outfit");
        model.addAttribute(new Outfit());
        model.addAttribute("locations", locationDao.findAll());
        model.addAttribute("occasions", occasionDao.findAll());
        model.addAttribute("peoples", peopleDao.findAll());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddOutfitForm(@ModelAttribute @Valid Outfit newOutfit,
                                       Errors errors, @RequestParam int locationId,
                                       @RequestParam int occasionId, @RequestParam int peopleId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Outfit");
            model.addAttribute("locations", locationDao.findAll());
            model.addAttribute("occasions", occasionDao.findAll());
            model.addAttribute("peoples", peopleDao.findAll());
            return "add";
        }

        Location loc = locationDao.findOne(locationId);
        newOutfit.setLocation(loc);
        Occasion occ = occasionDao.findOne(occasionId);
        newOutfit.setOccasion(occ);
        People peo=peopleDao.findOne(peopleId);
        newOutfit.setPeople(peo);
        outfitDao.save(newOutfit);
        model.addAttribute("title", "Browse Outfits");
        model.addAttribute("outfits", outfitDao.findAll());
        return "browse";
    }
}

