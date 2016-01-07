package uz.query.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by sherali on 1/7/16.
 */
@Controller
public class RobotsController {

    @RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
    public String getRobots(HttpServletRequest request) {
        return (Arrays.asList("query.uz", "www.query.uz").contains(request.getServerName())) ?
                "robotsAllowed" : "robotsDisallowed";
    }
}
