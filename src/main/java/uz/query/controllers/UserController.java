package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.query.models.RegistrationForm;
import uz.query.models.User;
import uz.query.repositories.UserRepository;
import uz.query.validator.RegistrationValidator;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationValidator registrationValidator;

    @RequestMapping("/login")
    public String login(Model model) {
//        model.addAttribute("userlist", userRepository.findByisDeleted(false));
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(@ModelAttribute RegistrationForm model, BindingResult result) {
        registrationValidator.validate(model, result);
        if (result.hasErrors()) {
            return "redirect:/registration";
        }
        User user = new User();
        user.setEmail(model.getEmail());
        user.setFullName(model.getFullName());
        user.setUserName(model.getUserName());
        user.setPassword(model.getPassword());
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
        userRepository.save(user);
        return "redirect:/userlist";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String greetingSubmit(@PathVariable Long userId) {
        User user = userRepository.findOne(userId);
        userRepository.checkAsDeleted(user);
        return "redirect:/userlist";
    }


}
