package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.models.Question;
import uz.query.repositories.QuestionRepository;

/**
 * Created by sherali on 12/13/15.
 */
@Controller
public class PostController {

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/ask_question", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "ask_question";
    }


    @RequestMapping(value = "/ask_question", method = RequestMethod.POST)
    public String addQuestionForm(@ModelAttribute Question question) {
        questionRepository.save(question);
        return "redirect:/ask_question";
    }
}
