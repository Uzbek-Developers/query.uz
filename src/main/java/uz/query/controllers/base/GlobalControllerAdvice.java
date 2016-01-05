package uz.query.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.query.Constants;
import uz.query.models.Tag;
import uz.query.models.ViewData;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.TagRepository;
import uz.query.security.SecurityUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/29/15 with love.
 */
@ControllerAdvice(basePackages = {"uz.query.controllers"})
public class GlobalControllerAdvice {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SecurityUtil securityUtil;

    private static final String USER = "user";
    private static final String VIEW_DATA = "viewData";

    @ModelAttribute
    public void addUserAttribute(Model model) {
        ViewData viewData = new ViewData("home");
        viewData.setTitle("Dasturlash bo`yicha savol-javoblar bo'yicha web loyiha");
        viewData.setMetaKeyword("savollar, savol, dasturlash tillari, algoritm, dastur, oop, uzbek dastur");
        viewData.setMetaDescription("Dasturlash bo'yicha savol-javoblar to'plami");

        model.addAttribute(Constants.TAG_LIST, tagRepository.findAll());

        List<Tag> tags = new LinkedList<>();
        Tag tag = tagRepository.findOne(Long.valueOf(1));//sayt haqidagi oxirgi 10 savollar uchun tagning id isi
        if (tag != null) {
            tags.add(tag);
            model.addAttribute(Constants.RULES_LIST, questionRepository.findFirst10ByTagsIn(tags));
        }
        model.addAttribute(Constants.VIEW_DATA, viewData);
        model.addAttribute(USER, securityUtil.getCurrentUser());
    }
}
