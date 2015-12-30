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
import uz.query.models.User;
import uz.query.repositories.TagRepository;
import uz.query.repositories.UserRepository;
import uz.query.security.SecurityUtil;

import java.util.List;

/**
 * Created by sherali on 12/15/15
 */
@Controller
public class TagController {
    //region <Repositories>
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityUtil securityUtil;
    //endregion

    //region <Request Mapping methods>
    @RequestMapping(value = "/tag/list")
    public String tags(Model model,
                       @PageableDefault(
                               size = (Constants.SMALL_PAGE_SIZE * 4),
                               sort = {"creationDate"},
                               direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute(Data.TAG_PAGE, tagRepository.findAll(pageable));
        return "tag_list";
    }

    @RequestMapping(value = "/tag/add", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        model.addAttribute("tag", new Tag());
        return "add-tag";
    }

    @RequestMapping(value = "/tag/{id}/info")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tag", tagRepository.findOne(id));
        return "about-tag";
    }

    @RequestMapping(value = "/tag/{id}/edit")
    public String editing(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tag", tagRepository.findOne(id));
        return "add_tag";
    }

    @RequestMapping(value = "/tag/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        tagRepository.delete(id);
        return "redirect:/tag/list";
    }

    @RequestMapping(value = {"/tag/save"}, method = RequestMethod.POST)
    public String addQuestionSubmit(@ModelAttribute Tag tag) {
        if (tag.getId() != null) {
            tag = tagRepository.findOne(tag.getId());
        }
        tag.setOwner(securityUtil.getCurrentUser());
        tagRepository.save(tag);

        return "redirect:/tag/" + tag.getId() + "/info";
    }
    //endregion

    //region <Methods for AJAX Requests>
    @RequestMapping(value = "/getTagList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Tag> getTagList(@RequestParam("key") String key) {
        return tagRepository.findFirst10ByTitleContaining(key);
    }

    @RequestMapping(value = "/getTagListByIds", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Tag> getTagList(@RequestParam("ids") List<Long> idList) {

        return tagRepository.findByIdIn(idList);
    }
    //endregion

    private interface Data {
        String TAG_PAGE = "tagPage";
    }
}
