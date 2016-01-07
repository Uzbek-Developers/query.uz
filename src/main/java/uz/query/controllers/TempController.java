package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.models.Answer;
import uz.query.models.Question;
import uz.query.models.Tag;
import uz.query.models.User;
import uz.query.repositories.AnswerRepository;
import uz.query.repositories.QuestionRepository;
import uz.query.repositories.TagRepository;
import uz.query.repositories.UserRepository;
import uz.query.security.SecurityUtil;
import uz.query.utils.StringUtils;
import uz.query.validator.RegistrationValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sherali Turdiev on 11/28/15.
 */
@Controller
public class TempController {
    //region <Autowired>
    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RegistrationValidator registrationValidator;
    //endregion

    //region <Request Mapping methods>
    @RequestMapping(value = "/fill", method = RequestMethod.GET)
    public String index(Model model) {
        if (userRepository.findByEmail("test@query.uz") == null) {
            //region <Users>
            User user1 = new User();
            user1.setDisplayName("Sinovdan o'tkazishga");
            user1.setEmail("test@query.uz");
            user1.setJob("Sinovchi");
            user1.setAbout("Sinash uchun");
            user1.setPassword(securityUtil.encodePassword("test"));
            user1 = userRepository.save(user1);
            user1.setLink("/user/" + user1.getId() + "/profile");
            user1 = userRepository.save(user1);


            User user2 = new User();
            user2.setDisplayName("Oddiy foydalanuvchi");
            user2.setEmail("user@query.uz");
            user2.setJob("Foydalanuvchi");
            user2.setAbout("Foydalanuvchi haqida");
            user2.setPassword(securityUtil.encodePassword("user"));
            user2 = userRepository.save(user2);
            user2.setLink("/user/" + user2.getId() + "/profile");
            user2 = userRepository.save(user2);

            User user3 = new User();
            user3.setDisplayName("Adminjon uchun");
            user3.setEmail("admin@query.uz");
            user3.setJob("Ma'mur");
            user3.setAbout("Ma'mur haqida");
            user3.setPassword(securityUtil.encodePassword("admin"));
            user3 = userRepository.save(user3);
            user3.setLink("/user/" + user3.getId() + "/profile");
            user3 = userRepository.save(user3);
            //endregion

            //region <Tegs>
            Tag tag1 = new Tag();
            tag1.setTitle("Sayt haqida");
            tag1.setShortDescription("Sayt haqidagi savollarni shu teg bilan qidirasiz va shu orqali topasiz");
            tag1.setContent("Sayt haqidagi savollarni shu teg bilan qidirasiz va shu orqali topasiz");
            tag1.setEditorContent("Sayt haqidagi savollarni shu teg bilan qidirasiz va shu orqali topasiz");
            tag1.setOwner(user3);
            tag1 = tagRepository.save(tag1);
            tag1.setPostLink("/tag/" + tag1.getId());
            tag1 = tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setTitle("Taklif");
            tag2.setShortDescription("Takliflarni bildirish uchun ");
            tag2.setContent("Turli takliflarni ifodalash uchun");
            tag2.setEditorContent("Turli takliflarni ifodalash uchun");
            tag2.setOwner(user2);
            tag2 = tagRepository.save(tag2);
            tag2.setPostLink("/tag/" + tag2.getId());
            tag2 = tagRepository.save(tag2);

            Tag tag3 = new Tag();
            tag3.setTitle("PHP");
            tag3.setShortDescription("PHP haqida qisqa ma'lumot");
            tag3.setContent("PHP haqida to'liq ma'lumot");
            tag3.setEditorContent("PHP haqida to'liq ma'lumot");
            tag3.setOwner(user1);
            tag3 = tagRepository.save(tag3);
            tag3.setPostLink("/tag/" + tag3.getId());
            tag3 = tagRepository.save(tag3);

            Tag tag4 = new Tag();
            tag4.setTitle("Java");
            tag4.setShortDescription("Java haqida qisqa ma'lumot");
            tag4.setContent("Java haqida to'liq ma'lumot");
            tag4.setEditorContent("Java haqida to'liq ma'lumot");
            tag4.setOwner(user2);
            tag4 = tagRepository.save(tag4);
            tag4.setPostLink("/tag/" + tag4.getId());
            tag4 = tagRepository.save(tag4);

            Tag tag5 = new Tag();
            tag5.setTitle("Javascript");
            tag5.setShortDescription("Javascript haqida qisqa ma'lumot");
            tag5.setContent("Javascript haqida to'liq ma'lumot");
            tag5.setEditorContent("Javascript haqida to'liq ma'lumot");
            tag5.setOwner(user3);
            tag5 = tagRepository.save(tag5);
            tag5.setPostLink("/tag/" + tag5.getId());
            tag5 = tagRepository.save(tag5);
            //endregion

            //region <Questions & Answers>
            Question question1 = new Question();
            question1.setTitle("Ovoz berishim shartmi?");
            question1.setContent("Ba'zi saytlarda o'qib qoldimki, biz savolga yoki javobga ovoz berishmaslik etikaga to'g'ri kelmas ekan. Nima uchun?");
            question1.setEditorContent("Ba'zi saytlarda o'qib qoldimki, biz savolga yoki javobga ovoz berishmaslik etikaga to'g'ri kelmas ekan. Nima uchun?");
            question1.setOwner(user2);
            List<Tag> tagList1 = new ArrayList<Tag>();
            tagList1.add(tag1);
            tagList1.add(tag4);
            question1.setTags(tagList1);

            Answer answer1 = new Answer();
            answer1.setContent("Foydali savolni foydali ekanligini ovozlar bilan tartiblash uchun ham, boshqalarga nafi tegishi uchun ham, o'sha post egasini darajasini bildirish uchun ham ovoz berish shart hisoblanadi! Undan tashqari yanada qiziqroq va aniqroq savollar yoki javob berishga qiziqtirish, rag'batlantirish bo'ladi.");
            answer1.setEditorContent("Foydali savolni foydali ekanligini ovozlar bilan tartiblash uchun ham, boshqalarga nafi tegishi uchun ham, o'sha post egasini darajasini bildirish uchun ham ovoz berish shart hisoblanadi! Undan tashqari yanada qiziqroq va aniqroq savollar yoki javob berishga qiziqtirish, rag'batlantirish bo'ladi.");
            answer1.setOwner(user3);
            answer1 = answerRepository.save(answer1);
            List<Answer> answerList1 = new ArrayList<>();
            answerList1.add(answer1);
            question1.setAnswers(answerList1);
            question1 = questionRepository.save(question1);
            question1.setPostLink(StringUtils.makeLinkFromTitle("/question/" + question1.getId() + "/", question1.getTitle()));

            question1 = questionRepository.save(question1);
            answer1.setPostLink(question1.getPostLink() + "#" + answer1.getId());
            answer1 = answerRepository.save(answer1);


            Question question2 = new Question();
            question2.setTitle("Uyga vazifa, Kurs ishilarni savol qilib berib bo'lmaydimi?");
            question2.setContent("Nima uchun uyga vazifa, kurs ishi, diplom ishi larni savol qilib berib bo'lmaysi?");
            question2.setEditorContent("Nima uchun uyga vazifa, kurs ishi, diplom ishi larni savol qilib berib bo'lmaysi?");
            question2.setOwner(user2);

            List<Tag> tagList2 = new ArrayList<>();
            tagList2.add(tag1);
            tagList2.add(tag4);
            question2.setTags(tagList2);

            Answer answer2 = new Answer();
            answer2.setContent("Bizda asosiy qoida bor: Bizni saytimizdan o'rganish uchun, tushunmay qolgan joyini so'rash uchun savol berishadi. Qanaqadir ishini qilib berish uchun emas. Savol egasi nimadir qilib ko'rishi ham so'raladi.");
            answer2.setEditorContent("Bizda asosiy qoida bor: Bizni saytimizdan o'rganish uchun, tushunmay qolgan joyini so'rash uchun savol berishadi. Qanaqadir ishini qilib berish uchun emas. Savol egasi nimadir qilib ko'rishi ham so'raladi.");
            answer2.setOwner(user1);
            answer2 = answerRepository.save(answer2);
            List<Answer> answerList2 = new ArrayList<>();
            answerList2.add(answer2);
            question2.setAnswers(answerList2);
            question2 = questionRepository.save(question2);
            question2.setPostLink(StringUtils.makeLinkFromTitle("/question/" + question2.getId() + "/", question2.getTitle()));
            question2 = questionRepository.save(question2);

            answer2.setPostLink(question2.getPostLink() + "#" + answer2.getId());
            answer2 = answerRepository.save(answer2);

            //endregion
        }

        model.addAttribute("loginUser", new User());
        model.addAttribute("registerUser", new User());
        return "auth";
    }

    //endregion

}
