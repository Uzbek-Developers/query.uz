package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.models.*;
import uz.query.repositories.*;
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
    private ArticleRepository articleRepository;

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
            tagList1.add(tag2);
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
            tagList2.add(tag2);
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

            
            
            Question question3 = new Question();
            question3.setTitle("Java 8 dagi yangi imkoniyatlar?");
            question3.setContent("Avvalgi Java versiyalaridan nimalari bilan farq qiladi? Nimalar o'zgargan");
            question3.setEditorContent("Avvalgi Java versiyalaridan nimalari bilan farq qiladi? Nimalar o'zgargan");
            question3.setOwner(user3);

            List<Tag> tagList3 = new ArrayList<>();
            tagList3.add(tag4);
            question3.setTags(tagList3);

            Answer answer3 = new Answer();
            answer3.setContent("Raqamida farq qiladi. :) 7 != 8");
            answer3.setEditorContent("Raqamida farq qiladi. :) 7 != 8");
            answer3.setOwner(user2);
            answer3 = answerRepository.save(answer3);
            List<Answer> answerList3 = new ArrayList<>();
            answerList3.add(answer3);
            question3.setAnswers(answerList3);
            question3 = questionRepository.save(question3);
            question3.setPostLink(StringUtils.makeLinkFromTitle("/question/" + question3.getId() + "/", question3.getTitle()));
            question3 = questionRepository.save(question3);

            answer3.setPostLink(question3.getPostLink() + "#" + answer3.getId());
            answer3 = answerRepository.save(answer3);



            Question question4 = new Question();
            question4.setTitle("Javada primitiv ma'lumot tiplari");
            question4.setContent("<p>Javadagi primitiv asl ma'nosi nima?</p>\n" +
                    "\n" +
                    "<p>Nima uchun \"primitiv ma'lumot TIP(TYPE)i\" deb  nomlangan?</p>");
            question4.setEditorContent("Javadagi primitiv asl ma'nosi nima?\n" +
                    "\n" +
                    "Nima uchun \"primitiv ma'lumot TIP(TYPE)i\" deb  nomlangan?");
            question4.setOwner(user2);

            List<Tag> tagList4 = new ArrayList<>();
            tagList4.add(tag4);
            question4.setTags(tagList4);

            Answer answer4 = new Answer();
            answer4.setContent("<p>Javada, har qanday o'zgaruvchi o'z tipiga ega bo'ladi. Bu yerda 2 turdagi tip bor: ishora(reference) tiplar va primitiv(primitive) tiplar. Ishora(reference) tiplar objectlarga bog'lanadi. Primitiv tiplar esa to'g'ridan-to'g'ri qiymatni o'z ichiga oladi. </p>\n" +
                    "\n" +
                    "<p>Primitiv tiplarni 4 guruhga ham bo'lsa bo'ladi:</p>\n" +
                    "\n" +
                    "<ol>\n" +
                    "<li><strong>Butun</strong> qiymatlar uchun:  byte, short, int  va long</li>\n" +
                    "<li><strong>Haqiqiy</strong> qiymatlar uchun:  float va double</li>\n" +
                    "<li><strong>Simvolli</strong> qiymatlar uchun: char (harflar va raqamlar kabi)</li>\n" +
                    "<li><strong>Mantiqiy</strong> qiymatlar uchun: boolean (true/false qiymatlarni o'zida aks ettiradi)</li>\n" +
                    "</ol>\n" +
                    "\n" +
                    "<p>Javada 8 ta primitiv tiplar mavjud: </p>\n" +
                    "\n" +
                    "<ul>\n" +
                    "<li><strong>int</strong> -  32 bit(4 bayt)li butun son</li>\n" +
                    "<li><strong>short</strong> - 16 bit(2 bayt)li butun son</li>\n" +
                    "<li><strong>long</strong> - 64 bit(8 bayt)li butun son</li>\n" +
                    "<li><strong>byte</strong> - 8 bit(1 bayt)li butun son</li>\n" +
                    "<li><strong>float</strong> - 32 bit(4 bayt) haqiqiy son</li>\n" +
                    "<li><strong>double</strong> - 64 bit(8 bayt)li haqiyqiy son</li>\n" +
                    "<li><strong>char</strong> - 16 bitli Unicode encoding scheme dan foydaluvchi simvollar </li>\n" +
                    "<li><strong>boolean</strong> - true(rost) va false(yolg'on) qiymat</li>\n" +
                    "</ul>");
            answer4.setEditorContent("Javada, har qanday o'zgaruvchi o'z tipiga ega bo'ladi. Bu yerda 2 turdagi tip bor: ishora(reference) tiplar va primitiv(primitive) tiplar. Ishora(reference) tiplar objectlarga bog'lanadi. Primitiv tiplar esa to'g'ridan-to'g'ri qiymatni o'z ichiga oladi. \n" +
                    "\n" +
                    "Primitiv tiplarni 4 guruhga ham bo'lsa bo'ladi:\n" +
                    "\n" +
                    " 1. **Butun** qiymatlar uchun:  byte, short, int  va long\n" +
                    " 2. **Haqiqiy** qiymatlar uchun:  float va double\n" +
                    " 3. **Simvolli** qiymatlar uchun: char (harflar va raqamlar kabi)\n" +
                    " 4. **Mantiqiy** qiymatlar uchun: boolean (true/false qiymatlarni o'zida aks ettiradi)\n" +
                    "\n" +
                    "Javada 8 ta primitiv tiplar mavjud: \n" +
                    "\n" +
                    " - **int** -  32 bit(4 bayt)li butun son\n" +
                    " - **short** - 16 bit(2 bayt)li butun son\n" +
                    " - **long** - 64 bit(8 bayt)li butun son\n" +
                    " - **byte** - 8 bit(1 bayt)li butun son\n" +
                    " - **float** - 32 bit(4 bayt) haqiqiy son\n" +
                    " - **double** - 64 bit(8 bayt)li haqiyqiy son\n" +
                    " - **char** - 16 bitli Unicode encoding scheme dan foydaluvchi simvollar \n" +
                    " - **boolean** - true(rost) va false(yolg'on) qiymat\n");
            answer4.setOwner(user1);
            answer4 = answerRepository.save(answer4);
            List<Answer> answerList4 = new ArrayList<>();
            answerList4.add(answer4);
            question4.setAnswers(answerList4);
            question4 = questionRepository.save(question4);
            question4.setPostLink(StringUtils.makeLinkFromTitle("/question/" + question4.getId() + "/", question4.getTitle()));
            question4 = questionRepository.save(question4);

            answer4.setPostLink(question4.getPostLink() + "#" + answer4.getId());
            answer4 = answerRepository.save(answer4);
            //endregion

            //region <Articles>
            Article article1 = new Article();
            article1.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article1.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article1.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article1.setOwner(user1);
            articleRepository.save(article1);
            article1.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article1.getId() + "/", article1.getTitle()));
            articleRepository.save(article1);

            Article article2 = new Article();
            article2.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article2.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article2.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article2.setOwner(user1);
            articleRepository.save(article2);
            article2.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article2.getId() + "/", article2.getTitle()));
            articleRepository.save(article2);

            Article article3 = new Article();
            article3.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article3.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article3.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article3.setOwner(user1);
            articleRepository.save(article3);
            article3.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article3.getId() + "/", article3.getTitle()));
            articleRepository.save(article3);

            Article article4 = new Article();
            article4.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article4.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article4.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article4.setOwner(user1);
            articleRepository.save(article4);
            article4.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article4.getId() + "/", article4.getTitle()));
            articleRepository.save(article4);

            Article article5 = new Article();
            article5.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article5.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article5.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article5.setOwner(user1);
            articleRepository.save(article5);
            article5.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article5.getId() + "/", article5.getTitle()));
            articleRepository.save(article5);

            Article article6 = new Article();
            article6.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article6.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article6.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article6.setOwner(user1);
            articleRepository.save(article6);
            article6.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article6.getId() + "/", article6.getTitle()));
            articleRepository.save(article6);

            Article article7 = new Article();
            article7.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article7.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article7.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article7.setOwner(user1);
            articleRepository.save(article7);
            article7.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article7.getId() + "/", article7.getTitle()));
            articleRepository.save(article7);

            Article article8 = new Article();
            article8.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article8.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article8.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article8.setOwner(user1);
            articleRepository.save(article8);
            article8.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article8.getId() + "/", article8.getTitle()));
            articleRepository.save(article8);

            Article article9 = new Article();
            article9.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article9.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article9.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article9.setOwner(user1);
            articleRepository.save(article9);
            article9.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article9.getId() + "/", article9.getTitle()));
            articleRepository.save(article9);

            Article article10 = new Article();
            article10.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article10.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article10.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article10.setOwner(user1);
            articleRepository.save(article10);
            article10.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article10.getId() + "/", article10.getTitle()));
            articleRepository.save(article10);

            Article article11 = new Article();
            article11.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article11.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article11.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article11.setOwner(user1);
            articleRepository.save(article11);
            article11.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article11.getId() + "/", article11.getTitle()));
            articleRepository.save(article11);

            Article article12 = new Article();
            article12.setTitle("Ma'lumotlarimizdan foydalanish tartibi.");
            article12.setContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article12.setEditorContent("Saytimizdagi ma'lumotlardan foydalanishda o'sha ma'lumotning to'liq web addresi va o'sha yozuv ega(lari)si ko'rsatilishi kerak.");
            article12.setOwner(user1);
            articleRepository.save(article12);
            article12.setPostLink(StringUtils.makeLinkFromTitle("/article/" + article12.getId() + "/", article12.getTitle()));
            articleRepository.save(article12);
            //endregion
        }

        model.addAttribute("loginUser", new User());
        model.addAttribute("registerUser", new User());
        return "auth";
    }

    //endregion

}
