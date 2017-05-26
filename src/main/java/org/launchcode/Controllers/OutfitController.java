package org.launchcode.Controllers;

import org.launchcode.Models.*;
import org.launchcode.Models.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by mettenichols on 4/12/17.
 */
@Controller
public class OutfitController {

    @Autowired
    TagDao tagDao;

    @Autowired
    OutfitDao outfitDao;




    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddOutfitForm(Model model) {
        model.addAttribute("title", "Add Outfit");
//         model.addAttribute(new Outfit());
         model.addAttribute("tags", tagDao.findAll());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddOutfitForm( Model model,
                                       @RequestParam List<String> tagList,
                                       @RequestParam MultipartFile image,
                                       @RequestParam String name,@RequestParam String description ) throws IOException {


        List<Tag> tagObjectList = new ArrayList<Tag>();
        for (String tag : tagList) {
            if (tagDao.findByName(tag) != null) {
                Tag newTag = (tagDao.findByName(tag));
                tagObjectList.add(newTag);

            } else {
                Tag newTag = new Tag(tag);
                tagDao.save(newTag);
                tagObjectList.add(newTag);
            }


        }

//        if(errors.hasErrors()) {
//            model.addAttribute("title", "Add Outfit");
//            model.addAttribute("tags", tagDao.findAll());
//            return "add";
//        }

        byte[] imageBytes = image.getBytes();
        Outfit persistentOutfit = new Outfit();
        persistentOutfit.setName(name);
        persistentOutfit.setDescription(description);
        persistentOutfit.setTagList(tagObjectList);
        persistentOutfit.setImage(imageBytes);

        outfitDao.save(persistentOutfit);

        ClientOutfit clientOutfit = new ClientOutfit();
        clientOutfit.setName(name);
        clientOutfit.setDescription(description);
        clientOutfit.setTagList(tagList);
        clientOutfit.setImage(imageBytes);
        clientOutfit.setId(outfitDao.findByName(name).getId());



        model.addAttribute("outfit", clientOutfit);
        model.addAttribute("id", clientOutfit.getId());
        model.addAttribute("title", "outfit added!" );

        return "browse";
    }

    @RequestMapping (value="/outfits/{id}/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable final int id, Model model) {
        byte[] bytes =outfitDao.findById(id).getImage();


        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="outfits", method = RequestMethod.GET)
    public String displayAllOutfits(Model model) {

        model.addAttribute("outfits", outfitDao.findAll());

        return "outfits";
    }

    @RequestMapping(value="outfits/{id}", method = RequestMethod.GET)
    public String displayOutfitDetail(@PathVariable final int id, Model model) {

      Outfit outfit = outfitDao.findById(id);
        List<String> tagStrings = new ArrayList<>();
        for (Tag tag: outfit.getTagList()) {
            tagStrings.add(tag.getName());

        }

        model.addAttribute("outfit", outfit);
       model.addAttribute("tagList", tagStrings);

        return "browse";
    }
}

