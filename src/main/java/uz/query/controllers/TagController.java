package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.query.Constants;
import uz.query.repositories.TagRepository;


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

}
