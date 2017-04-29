package org.launchcode.Controllers;

import org.launchcode.Models.Data.LocationDao;
import org.launchcode.Models.Data.OccasionDao;
import org.launchcode.Models.Data.PeopleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mettenichols on 4/29/17.
 */
@Controller
@RequestMapping("tags")
public class TagController {
    @Autowired
    LocationDao locationDao;

    @Autowired
    OccasionDao occasionDao;

    @Autowired
    PeopleDao peopleDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("locations", locationDao.findAll());
        model.addAttribute("occasions", occasionDao.findAll());
        model.addAttribute("peoples", peopleDao.findAll());

        model.addAttribute("title", "Tags");

        return "tags/index";
    }


}
