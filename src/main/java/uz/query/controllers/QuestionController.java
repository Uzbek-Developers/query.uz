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
import uz.query.models.*;
import uz.query.models.enums.StatusType;
import uz.query.repositories.*;
import uz.query.security.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15.
 */
@Controller
public class QuestionController {
    //region <Repositories>
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private SecurityUtil securityUtil;
    //endregion

    //region <Request Mapping methods>
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
        Question question = questionRepository.findOne(id);
        PostStatus status = new PostStatus(question.getOwner());
        question.setPostStatus(status);
        model.addAttribute("question", question);
        model.addAttribute("statusTypeList", StatusType.values());
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
    public String addQuestionSubmit(@ModelAttribute Question formQuestion, HttpServletRequest request) {
        if (formQuestion.getId() != null) {
            formQuestion = questionRepository.findOne(formQuestion.getId());
        }
        String selectedTagList = request.getParameter("tagIdList");
        List<String> ids = Arrays.asList(selectedTagList.split(","));
        List<Tag> list = new LinkedList<Tag>();
        for (String id : ids) {
            list.add(tagRepository.findOne(Long.valueOf(id)));
        }
        formQuestion.setTags(list);
        formQuestion.setOwner(securityUtil.getCurrentUser());

        PostStatus status = new PostStatus(securityUtil.getCurrentUser());
        formQuestion.setPostStatus(status);

        questionRepository.save(formQuestion);

        return "redirect:/home";
    }

    @RequestMapping(value = "/question/add_answer", method = RequestMethod.POST)
    public String addQuestionSubmit(HttpServletRequest request, @ModelAttribute("newAnswer") Answer answer) {
        answer.setOwner(securityUtil.getCurrentUser());
        answer = answerRepository.save(answer);

        Long questionId = Long.parseLong(request.getParameter("questionId"));

        Question q = questionRepository.findOne(questionId);
        List<Answer> otherAnswers = q.getAnswers();
        otherAnswers.add(answer);

        questionRepository.save(q);

        return "redirect:/question/" + questionId;
    }

    @RequestMapping(value = "/warnAdministration", method = RequestMethod.POST)
    public String warnAboutQuestion(HttpServletRequest request) {
        String reason = request.getParameter("reason");

        String postType = request.getParameter("postType");
        String statusType = request.getParameter("statusType");

        Long parentId = Long.parseLong(request.getParameter("parentId"));
        Long postId = Long.parseLong(request.getParameter("postId"));
//        List<Answer> otherAnswers = q.getAnswers();
//        otherAnswers.add(answer);
        Question q = questionRepository.findOne(parentId);
        q.setStatusType(StatusType.getTypeByName(statusType));
        q.setReason(reason);

        questionRepository.save(q);

            return "redirect:/question/" + parentId;
    }

    @RequestMapping(value = "/setVote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String setVote(@RequestParam("rank") String rankVote, @RequestParam("questionId") Long questionId) {
        int rank = rankVote.equals("up") ? +1 : (rankVote.equals("down") ? -1 : 0);
//        Long questionId = Long.parseLong(question);
        if (rank != 0 && questionId > 0) {
            User user = userRepository.findOne(Long.valueOf(1));
            Question question = questionRepository.findOne(questionId);

            Vote myVote = null;
            if (question.getVotes().size() > 0)
                myVote = question.getVotes().stream().filter(v -> v.getOwner().getId().equals(user.getId())).findFirst().get();
            Vote oldVote = myVote;
            if (myVote != null) {
                myVote.setRank(rank);
                myVote = voteRepository.save(myVote);
                question.getVotes().set(question.getVotes().indexOf(oldVote), myVote);
            } else {
                myVote = new Vote();
                myVote.setOwner(user);
                myVote.setRank(rank);
                myVote = voteRepository.save(myVote);
                question.getVotes().add(myVote);
            }
            questionRepository.save(question);

        }
        return "";
    }


    @RequestMapping(value = "/question/tagged/{id}")
    public String questionsByTagName(@PathVariable("id") Long id, Model model,
                                     @PageableDefault(
                                             size = Constants.SMALL_PAGE_SIZE,
                                             sort = {"creationDate"},
                                             direction = Sort.Direction.DESC) Pageable pageable) {
        List<Tag> tags = new LinkedList<>();
        tags.add(tagRepository.findOne(id));
        model.addAttribute(Data.QUESTION_PAGE, questionRepository.findAllByTagsIn(tags, pageable));
        return "home";
    }
    //endregion


    public interface Data {
        String QUESTION_PAGE = "questionPage";
    }

}