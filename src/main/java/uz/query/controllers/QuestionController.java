package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.Constants;
import uz.query.models.Answer;
import uz.query.models.Question;
import uz.query.models.Tag;
import uz.query.models.User;
import uz.query.repositories.AnswerRepository;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.TagRepository;
import uz.query.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15.
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;

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
        model.addAttribute("newAnswer", new Answer());

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


    @RequestMapping(value = "/ask_question", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        Question question = new Question();
//        question.setTitle("question title");
//        question.setContent("question content");

        model.addAttribute("question", question);
//        model.addAttribute("tagIdList", new String[]{"josd", "v"});
        return "ask_question";
    }

    @RequestMapping(value = "/ask_question", method = RequestMethod.POST)
    public String addQuestionSubmit(@ModelAttribute Question question, HttpServletRequest request) {
        String selectedTagList = request.getParameter("tagIdList");
        List<String> ids = Arrays.asList(selectedTagList.split(","));
        List<Tag> list = new LinkedList<>();
        for (String id : ids) {
            list.add(tagRepository.findOne(Long.valueOf(id)));
        }
        question.setTags(list);
        User u = userRepository.findOne(Long.valueOf(1));
        question.setOwner(u);
        questionRepository.save(question);

        return "redirect:/home";
    }

    @RequestMapping(value = "/question/add_answer", method = RequestMethod.POST)
    public String addQuestionSubmit(@ModelAttribute("question") Question question, @ModelAttribute("newAnswer") Answer answer) {
        User u = userRepository.findOne(Long.valueOf(1));
        Question q = questionRepository.findOne(question.getId());
        answer.setOwner(u);
        answer = answerRepository.save(answer);
        List<Answer> otherAnswers = q.getAnswers();
        otherAnswers.add(answer);
        questionRepository.save(q);

        return "redirect:/question/" + question.getId();
    }

    private interface Data {
        String QUESTION_PAGE = "questionPage";
    }

}
