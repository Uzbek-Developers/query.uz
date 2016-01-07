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
import uz.query.models.enums.FlagType;
import uz.query.models.enums.StatusType;
import uz.query.repositories.ArticleRepository;
import uz.query.repositories.UserRepository;
import uz.query.security.SecurityUtil;
import uz.query.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sherali on 12/15/15
 */
@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    //region <Repositories>
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityUtil securityUtil;
    //endregion

    //region <Request Mapping methods>
    @RequestMapping(value = "/list")
    public String tags(Model model,
                       @PageableDefault(
                               size = (Constants.SMALL_PAGE_SIZE * 4),
                               sort = {"creationDate"},
                               direction = Sort.Direction.DESC) Pageable pageable) {

        ViewData viewData = new ViewData("Article_List");
        viewData.setTitle("Saytning matnlari, qoidalari, maqolalari ro'yxati");
        viewData.setMetaKeyword("query.uz maqolalar, qoidalar, qo'llanma");
        viewData.setMetaDescription("Query.uzdagi maqolalar, qoidalar, matnlar to'plami haqidagi sahifalar");
        model.addAttribute(Constants.VIEW_DATA, viewData);

        model.addAttribute(Constants.PAGE, articleRepository.findAll(pageable));
        return "list-article";
    }
    @RequestMapping(value = "/{id}/{title}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = articleRepository.findOne(id);

        ViewData viewData = new ViewData("Article_View");
        viewData.setTitle(article.getTitle());
        viewData.setMetaDescription(article.getContent());
        model.addAttribute(Constants.VIEW_DATA, viewData);

        model.addAttribute("article", article);

        return "details-article";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String enterAddQuestionForm(@PathVariable("id") Long id, Model model) {
        Article article = articleRepository.findOne(id);

        ViewData viewData = new ViewData("Artilce_Edit");
        viewData.setTitle(article.getTitle());
        viewData.setMetaKeyword(article.getTitle());
        viewData.setMetaDescription(article.getContent());
        model.addAttribute(Constants.VIEW_DATA, viewData);

        model.addAttribute("article", article);
        return "add-article";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        articleRepository.delete(id);
        return "redirect:/home";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String enterAddQuestionForm(Model model) {
        Article article = new Article();
        ViewData viewData = new ViewData("Article_Add");
        viewData.setTitle("Maqola qo'shish");
        viewData.setMetaKeyword("maqola qo'shish, qoida qo'shish");
        viewData.setMetaDescription("Query.uzga yangi maqola qo'shish");
        model.addAttribute(Constants.VIEW_DATA, viewData);

        model.addAttribute("article", article);

        return "add-article";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addQuestionSubmit(@ModelAttribute Article formObj, HttpServletRequest request) {
        if (formObj.getId() != null) {
            formObj = articleRepository.findOne(formObj.getId());
        }
        String selectedTagList = request.getParameter("tagIdList");
//        List<String> ids = Arrays.asList(selectedTagList.split(","));
//        List<Tag> list = new LinkedList<Tag>();
//        for (String id : ids) {
//            list.add(articleRepository.findOne(Long.valueOf(id)));
//        }

        formObj.setOwner(securityUtil.getCurrentUser());
        if (formObj.getId() == null) {
            articleRepository.save(formObj);
        }

        formObj.setPostLink(StringUtils.makeLinkFromTitle("/article/" + formObj.getId() + "/", formObj.getTitle()));

        articleRepository.save(formObj);

        return "redirect:/home";
    }
    //endregion

    //region <AJAX request methods>
    @RequestMapping(value = "/getArticlesList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Article> getTagList(@RequestParam("key") String key) {
        return articleRepository.findFirst10ByTitleContaining(key);
    }
    //endregion

    private interface Data {
        String PAGE = "page";
    }
}
