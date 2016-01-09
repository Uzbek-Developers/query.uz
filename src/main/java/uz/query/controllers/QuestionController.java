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
import uz.query.models.base.Post;
import uz.query.models.enums.FlagType;
import uz.query.models.enums.PostType;
import uz.query.models.enums.StatusType;
import uz.query.repositories.*;
import uz.query.repositories.base.BaseRepository;
import uz.query.security.SecurityUtil;
import uz.query.utils.StringUtils;

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
        ViewData viewData = new ViewData("Question_List");
        viewData.setTitle("Dasturlashga oid savollar to'plami");
        viewData.setMetaKeyword("savollar ro'yxati, savol - javob, dasturlash tillari, algoritm, dastur, oop");
        viewData.setMetaDescription("Dasturlash bo`yicha oxirgi berilgan savollar ro`yxati");
        viewData.setViewLink("/question/list");

        model.addAttribute(Constants.VIEW_DATA, viewData);

        List<Question> topQuestions = questionRepository.findAll();
        model.addAttribute(Data.TOP_QUESTIONS, topQuestions);

        model.addAttribute(Constants.PAGE, questionRepository.findAll(pageable));
        return "list-question";
    }

    @RequestMapping(value = {"/question/{id}/{title}", "/question/{id}"})
    public String detail(@PathVariable("id") Long id, Model model) {
        Question question = questionRepository.findOne(id);
        boolean isDisabledAddingAnswer = StatusType.isDisabledAddingAnswer(question.getStatusType());

        ViewData viewData = new ViewData("Question_View");
        viewData.setTitle(question.getTitle());
        viewData.setMetaKeyword(question.getTags().toString());
        viewData.setMetaDescription(question.getContent());
        viewData.setViewLink(question.getPostLink());
        model.addAttribute(Constants.VIEW_DATA, viewData);

        List<Question> relatedQuestions = questionRepository.findAll();
        model.addAttribute(Data.RELATED_QUESTIONS, relatedQuestions);

        model.addAttribute("isDisabledAddingAnswer", isDisabledAddingAnswer);
        model.addAttribute("question", question);
        model.addAttribute("statusTypeList", StatusType.values());
        model.addAttribute("flagTypeList", FlagType.values());
        model.addAttribute("newAnswer", new Answer());

        return "details-question";
    }

    @RequestMapping(value = "/question/edit/{id}", method = RequestMethod.GET)
    public String enterAddQuestionForm(@PathVariable("id") Long id, Model model) {
        Question question = questionRepository.findOne(id);

        ViewData viewData = new ViewData("Question_Edit");
        viewData.setTitle(question.getTitle());
        viewData.setMetaKeyword(question.getTags().toString());
        viewData.setMetaDescription(question.getContent());
        viewData.setViewLink(question.getPostLink());

        model.addAttribute(Constants.VIEW_DATA, viewData);


        model.addAttribute("question", question);
        return "add-question";
    }

    @RequestMapping(value = "/question/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        questionRepository.delete(id);
        return "redirect:/home";
    }


    @RequestMapping(value = "/question/add", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        Question question = new Question();
        ViewData viewData = new ViewData("Question_Add");
        viewData.setTitle("Savol so'rash");
        viewData.setMetaKeyword("savol berish, savol qo'shish");
        viewData.setMetaDescription("Query.uzga dasturlash bo'yicha savol qo'shish, savol berish");
        viewData.setViewLink("/question/add");
        model.addAttribute(Constants.VIEW_DATA, viewData);


        model.addAttribute("question", question);

        return "add-question";
    }

    @RequestMapping(value = "/save_question", method = RequestMethod.POST)
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
        if (formQuestion.getId() == null) {
            questionRepository.save(formQuestion);
        }

        formQuestion.setPostLink(StringUtils.makeLinkFromTitle("/question/" + formQuestion.getId() + "/", formQuestion.getTitle()));

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

    @RequestMapping(value = "/stateStatusOfQuestion", method = RequestMethod.POST)
    public String stateStatusOfQuestion(HttpServletRequest request) {
        String reason = request.getParameter("reason");

        String postType = request.getParameter("postType");
        String statusType = request.getParameter("statusType");
        String statusLink = request.getParameter("statusLink");//TODO statusLink va reason keyinchalik textareaga o'zgartirildi

        Long parentId = Long.parseLong(request.getParameter("parentId"));
        Long postId = Long.parseLong(request.getParameter("postId"));

        Question q = questionRepository.findOne(parentId);
        q.setStatusType(StatusType.getTypeByName(statusType));
        q.setStatusType(StatusType.getTypeByName(statusType));
        q.setStatusLink(statusLink);
        q.setReason(reason);

        questionRepository.save(q);

        return "redirect:/question/" + parentId;
    }

    @RequestMapping(value = "/warnAdministration", method = RequestMethod.POST)
    public String warnAboutQuestion(HttpServletRequest request) {
        String reason = request.getParameter("reason");
        String flag = request.getParameter("flag");

        Long parentId = Long.parseLong(request.getParameter("parentId"));
        Long postId = Long.parseLong(request.getParameter("postId"));
        String postType = request.getParameter("postType");

        Question question = questionRepository.findOne(parentId);

        if (postType.equals("Question")) {
            question.setFlagType(FlagType.getTypeByName(flag));
            question.setReason(reason);
        } else {
            Answer answer = answerRepository.findOne(postId);
            answer.setFlagType(FlagType.getTypeByName(flag));
            answerRepository.save(answer);

        }

        questionRepository.save(question);

        return "redirect:/question/" + parentId;
    }

    @RequestMapping(value = "/setQuestionVote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String setQuestionVote(@RequestParam("rank") String rankVote, @RequestParam("id") Long id) {
        int rank = rankVote.equals("up") ? +1 : (rankVote.equals("down") ? -1 : 0);
//        Long questionId = Long.parseLong(question);
        if (rank != 0 && id > 0) {
            User user = securityUtil.getCurrentUser();
            Question question = questionRepository.findOne(id);

            Vote myVote = null;
            if (question.getVotes().size() > 0) {
                myVote = question.getVotes().stream().filter(v -> v.getOwner().getId().equals(user.getId())).findFirst().get();
            }
            if (myVote == null) {
                myVote = new Vote();
                myVote.setOwner(user);
            } else {
                if (myVote.getRank() == rank)
                    return "";
                question.getVotes().remove(myVote);
            }
            myVote.setRank(rank);
            myVote = voteRepository.save(myVote);
            question.getVotes().add(myVote);
            question.setVoteCount(question.getVoteCount() + rank);
            questionRepository.save(question);
        }
        return "";
    }

    @RequestMapping(value = "/setVote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object setAnswerVote(@RequestParam("rank") String rankVote, @RequestParam("id") Long id, @RequestParam("postType") PostType postType) {

        int rank = rankVote.equals("up") ? +1 : (rankVote.equals("down") ? -1 : 0);
        if (rank != 0 && id > 0) {

            User user = securityUtil.getCurrentUser();
            BaseRepository repository = (postType == PostType.Answer) ? answerRepository : questionRepository;
            Post post = (Post) repository.findOne(id);

            Vote myVote = null;
            if (post.getVotes().size() > 0) {
                try {
                    myVote = post.getVotes().stream().filter(v -> v.getOwner().getId().equals(user.getId())).findFirst().get();
                } catch (Exception e) {
                    myVote = null;
                }
                if (myVote != null)
                    post.getVotes().remove(myVote);
            }
            if (myVote == null) {
                myVote = new Vote();
                myVote.setOwner(user);
                myVote.setRank(rank);
            } else {
                post.setVoteCount(post.getVoteCount() - myVote.getRank());
                if (myVote.getRank() == rank) {
                    myVote.setRank(0);
                    voteRepository.delete(myVote);
                } else myVote.setRank(rank);
            }

            post.setVoteCount(post.getVoteCount() + myVote.getRank());
            myVote = voteRepository.save(myVote);
            post.getVotes().add(myVote);
            post = (Post) repository.save(post);

            final Vote finalMyVote = myVote;
            final Post finalPost = post;

            Object result = new Object() {
                public int rank = finalMyVote.getRank();
                public int voteCount = finalPost.getVoteCount();
            };
            return result;
        }
        return new Object();
    }


    @RequestMapping(value = "/question/tagged/{id}")
    public String questionsByTagName(@PathVariable("id") Long id, Model model,
                                     @PageableDefault(
                                             size = Constants.SMALL_PAGE_SIZE,
                                             sort = {"creationDate"},
                                             direction = Sort.Direction.DESC) Pageable pageable) {
        List<Tag> tags = new LinkedList<>();
        tags.add(tagRepository.findOne(id));
        model.addAttribute(Constants.PAGE, questionRepository.findAllByTagsIn(tags, pageable));
        return "home";
    }
    //endregion


    public interface Data {
        String PAGE = "page";
        String TOP_QUESTIONS = "topQuestions";
        String RELATED_QUESTIONS = "relatedQuestions";
    }

}