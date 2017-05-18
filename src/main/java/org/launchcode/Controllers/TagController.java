package org.launchcode.Controllers;

import org.launchcode.Models.Data.TagDao;
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
    TagDao tagDao;



    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("tags", tagDao.findAll());
        model.addAttribute("title", "Tags");

        return "tags/index";
    }


}
