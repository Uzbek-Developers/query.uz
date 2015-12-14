package uz.query.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.query.repositories.UserRepository;

/**
 * Created by sherali on 12/13/15.
 */

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/", "/home"})
    public String home(Model model) {
//        model.addAttribute("userlist", userRepository.findByisDeleted(false));
        return "home";
    }
}
