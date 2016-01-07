package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.query.Constants;
import uz.query.models.Tag;
import uz.query.models.ViewData;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.TagRepository;
import uz.query.security.SecurityUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sherali on 12/15/15
 */
@Controller
public class HelpController {
    //region <Repositories>
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SecurityUtil securityUtil;
    //endregion

    //region <Request Mapping methods>
    @RequestMapping(value = {"help", "/help/list"})
    public String tags(Model model,
                       @PageableDefault(
                               size = (Constants.SMALL_PAGE_SIZE * 4),
                               sort = {"creationDate"},
                               direction = Sort.Direction.DESC) Pageable pageable) {

        ViewData viewData = new ViewData("Help_List");
        viewData.setTitle("Saytga aloqador savollar/takliflar ro'yxati");
        viewData.setMetaKeyword("taklif, izoh, qoida, tartib, ");
        viewData.setMetaDescription("Query.uzga tegishli bo'lgan savollar, takliflar, qoidalar to'plami");
        model.addAttribute(Constants.VIEW_DATA, viewData);

        if (tagRepository.findOne(Long.valueOf(1)) !=null) {

            List<Tag> tags = new LinkedList<>();
            tags.add(tagRepository.findOne(Long.valueOf(1)));

            model.addAttribute(Constants.PAGE, questionRepository.findAllByTagsIn(tags, pageable));

        }
        return "list-site-question";
    }
    //endregion


    private interface Data {
        String PAGE = "page";
    }
}
