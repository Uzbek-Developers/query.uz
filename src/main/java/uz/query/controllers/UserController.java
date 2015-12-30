package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.query.models.RegistrationForm;
import uz.query.models.User;
import uz.query.repositories.UserRepository;
import uz.query.security.SecurityUtil;
import uz.query.validator.RegistrationValidator;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Controller
public class UserController {
    //region <Autowired>
    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationValidator registrationValidator;
    //endregion

    //region <Request Mapping methods>
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("loginUser", new User());
        model.addAttribute("registerUser", new User());
        return "auth";
    }
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String loginForm(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(@ModelAttribute RegistrationForm model, BindingResult result) {
        registrationValidator.validate(model, result);
        if (result.hasErrors()) {
            return "redirect:/auth";
        }
        User user = new User();
        user.setEmail(model.getEmail());
        user.setPassword(securityUtil.encodePassword(model.getPassword()));
        user.setDisplayName(model.getDisplayName());
        user.setReputation(0);
        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping("/userlist")
    public String userlist(Model model) {
        model.addAttribute("userlist", userRepository.findByisDeleted(false));
        return "userlist";
    }

    @RequestMapping(value = "/adduserform", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "user_add";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute User user) {
        user.setPassword(securityUtil.encodePassword(user.getPassword()));
        userRepository.save(user);
        return "redirect:/userlist";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String greetingSubmit(@PathVariable Long userId) {
        User user = userRepository.findOne(userId);
        userRepository.checkAsDeleted(user);
        return "redirect:/userlist";
    }
    //endregion

}
