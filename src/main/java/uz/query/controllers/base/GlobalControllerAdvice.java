package uz.query.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.query.Constants;
import uz.query.models.ViewData;
import uz.query.security.SecurityUtil;

/**
 * Created by Mirjalol Bahodirov on 12/29/15 with love.
 */
@ControllerAdvice(basePackages = {"uz.query.controllers"})
public class GlobalControllerAdvice {

    @Autowired
    private SecurityUtil securityUtil;

    private static final String USER = "user";
    private static final String VIEW_DATA = "viewData";

    @ModelAttribute
    public void addUserAttribute(Model model) {
        ViewData viewData = new ViewData("home");
        viewData.setTitle("Dasturlash bo`yicha savol-javoblar bo'yicha web loyiha");
        viewData.setMetaKeyword("savollar, savol, dasturlash tillari, algoritm, dastur, oop, uzbek dastur");
        viewData.setMetaDescription("Dasturlash bo'yicha savol-javoblar to'plami");
        model.addAttribute(Constants.VIEW_DATA, viewData);
        model.addAttribute(USER, securityUtil.getCurrentUser());
    }
}
