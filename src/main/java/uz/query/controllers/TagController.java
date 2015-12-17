package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.expression.Lists;
import uz.query.Constants;
import uz.query.models.Tag;
import uz.query.repositories.TagRepository;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @RequestMapping(value = "/getTagList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Tag> getTagList(@RequestBody String key) {

//        Tag s = key;
        List<Tag> list = new ArrayList<Tag>();
//        list.add(tagRepository.findOne(Long.valueOf(1)));
//        list.add(tagRepository.findOne(Long.valueOf(2)));
//        list.add(tagRepository.findOne(Long.valueOf(3)));
//        list.add(tagRepository.findOne(Long.valueOf(4)));
        return list;
    }

}
