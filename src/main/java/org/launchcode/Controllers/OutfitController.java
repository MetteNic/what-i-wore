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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

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
        model.addAttribute("title", "What are you wearing?");
         model.addAttribute(new ClientOutfit());
         model.addAttribute("tags", tagDao.findAll());
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddOutfitForm( Model model,@ModelAttribute  @Valid ClientOutfit clientOutfit,
                                        Errors errors,
                                        @RequestParam String name, @RequestParam String description,
                                        @RequestParam MultipartFile image, @RequestParam List<String> tagList) throws IOException {


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

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Outfit");
            model.addAttribute("tags", tagDao.findAll());
            return "add";
        }

        byte[] imageBytes = image.getBytes();
        Outfit persistentOutfit = new Outfit();
        persistentOutfit.setName(name);
        persistentOutfit.setDescription(description);
        persistentOutfit.setTagList(tagObjectList);
        persistentOutfit.setImage(imageBytes);

        outfitDao.save(persistentOutfit);


        model.addAttribute("outfit", persistentOutfit);
        model.addAttribute("id", persistentOutfit.getId());
        model.addAttribute("title", "outfit added!" );
        model.addAttribute("header", "Your outfit was succesfully added with these details:");
        model.addAttribute("tagStrings", tagList);

        return "browse";
    }

    @RequestMapping (value="/outfits/{id}/image", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable final int id, Model model) {
        byte[] bytes = outfitDao.findById(id).getImage();

        if(bytes==null || bytes.length==0) {
            bytes = getNoPhotoImage();
        }
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);
    }

    private byte[] getNoPhotoImage() {
        byte[] bytes;
        InputStream nophotoStream = this.getClass().getResourceAsStream("/static/images/nophoto.jpg");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int b;
        try {
            while ((b = nophotoStream.read()) >= 0) {
                os.write(b);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = os.toByteArray();
        return bytes;
    }

    @RequestMapping(value="outfits", method = RequestMethod.GET)
    public String displayAllOutfits(Model model) {

        model.addAttribute("outfits", outfitDao.findAll());
        model.addAttribute("title", "Looking for some inspiration??");

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
       model.addAttribute("tagStrings", tagStrings);
       model.addAttribute("header", "Here are the details of your outfit");


        return "browse";
    }
}

