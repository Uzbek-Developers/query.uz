package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.query.Constants;
import uz.query.models.Tag;
import uz.query.repositories.TagRepository;

import java.util.List;

/**
 * Created by sherali on 12/15/15.
 */
@Controller
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/tags")
    public String tags(Model model,
                       @PageableDefault(
                               size = Constants.SMALL_PAGE_SIZE,
                               sort = {"creationDate"},
                               direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute(Data.TAG_PAGE, tagRepository.findAll(pageable));
        return "tag_list";
    }

    private interface Data {
        String TAG_PAGE = "tagPage";
    }

    @RequestMapping(value = "/getTagList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Tag> getTagList(@RequestParam("key") String key) {
        return tagRepository.findFirst10ByNameContaining(key);
    }

}
