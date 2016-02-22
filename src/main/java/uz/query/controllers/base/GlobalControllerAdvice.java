package uz.query.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.query.Constants;
import uz.query.models.Tag;
import uz.query.models.ViewData;
import uz.query.repositories.AnswerRepository;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.TagRepository;
import uz.query.repositories.VoteRepository;
import uz.query.security.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
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
    private AnswerRepository answerRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SecurityUtil securityUtil;

    private static final String USER = "user";
    private static final String VIEW_DATA = "viewData";

    @ModelAttribute
    public void addUserAttribute(Model model, HttpServletRequest request) {
        ViewData viewData = new ViewData("home");
        viewData.setTitle("Dasturlash bo`yicha savol-javoblar bo'yicha web loyiha");
        viewData.setMetaKeyword("savollar, savol, dasturlash tillari, algoritm, dastur, oop, uzbek dastur");
        viewData.setMetaDescription("Dasturlash bo'yicha savol-javoblar to'plami");
        viewData.setViewLink(request.getRequestURI());

        model.addAttribute(Constants.TAG_LIST, tagRepository.findAll());
        if (securityUtil.getCurrentUser() != null) {
            model.addAttribute(Constants.USER_QUESTION_LIST, questionRepository.findAllByOwnerId(securityUtil.getCurrentUser().getId()));
            model.addAttribute(Constants.USER_ANSWER_LIST, answerRepository.findAllByOwnerId(securityUtil.getCurrentUser().getId()));
            model.addAttribute(Constants.USER_VOTE_LIST, voteRepository.findAllByOwnerId(securityUtil.getCurrentUser().getId()));
        }

        List<Tag> tags = new LinkedList<>();
        Tag tag = tagRepository.findOne((long) 1);//sayt haqidagi oxirgi 10 savollar uchun tagning id isi
        if (tag != null) {
            tags.add(tag);
            model.addAttribute(Constants.RULES_LIST, questionRepository.findFirst10ByTagsIn(tags));
        }
        model.addAttribute(Constants.VIEW_DATA, viewData);
        model.addAttribute(USER, securityUtil.getCurrentUser());
    }
}
