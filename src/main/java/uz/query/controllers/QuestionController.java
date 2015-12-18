package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.Constants;
import uz.query.repositories.QuestionRepository;

/**
 * Created by Mirjalol Bahodirov on 12/14/15.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = {"/", "/home", "/question/list"})
    public String home(Model model,
                       @PageableDefault(
                               size = Constants.SMALL_PAGE_SIZE,
                               sort = {"creationDate"},
                               direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute(Data.QUESTION_PAGE, questionRepository.findAll(pageable));
        return "home";
    }

    @RequestMapping(value = "/question/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionRepository.findOne(id));

        return "details";
    }

    @RequestMapping(value = "/question/edit/{id}", method = RequestMethod.GET)
    public String enterAddQuestionForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("question", questionRepository.findOne(id));
        return "ask_question";
    }

    @RequestMapping(value = "/question/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        questionRepository.delete(id);
        return "redirect:/home";
    }

    private interface Data {
        String QUESTION_PAGE = "questionPage";
    }

}
