package org.launchcode.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mettenichols on 4/12/17.
 */


@Controller
public class HomeController {

    @RequestMapping(value = "home")
    public String index(Model model) {
        model.addAttribute("title", "What I Wore");
        return "index";
    }

}

