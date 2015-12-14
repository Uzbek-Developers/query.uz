package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.models.Question;
import uz.query.models.User;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.UserRepository;

/**
 * Created by sherali on 12/13/15.
 */
@Controller
public class PostController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/ask_question", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        Question question = new Question();
        question.setQuestionTitle("question title");
        question.setQuestionContent("question content");

        model.addAttribute("question", question);
        return "ask_question";
    }


    @RequestMapping(value = "/ask_question", method = RequestMethod.POST)
    public String addQuestionSubmit(@ModelAttribute Question question) {
        User u = userRepository.findOne(Long.valueOf(1));
        question.setQuestionOwner(u);
        questionRepository.save(question);
        return "redirect:/home";
    }
}
