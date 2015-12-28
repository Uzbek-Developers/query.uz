package uz.query.controllers.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import uz.query.security.SecurityUtil;

/**
 * Created by Mirjalol Bahodirov on 12/29/15 with love.
 */
@ControllerAdvice(basePackages = {"uz.query.controllers"})
public class GlobalControllerAdvice {

    @Autowired
    private SecurityUtil securityUtil;

    private static final String USER = "user";

    @ModelAttribute
    public void addUserAttribute(Model model) {
        model.addAttribute(USER, securityUtil.getCurrentUser());
    }

}
