package uz.query.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.query.models.XmlUrl;
import uz.query.models.XmlUrlSet;

/**
 * Created by sherali on 1/7/16.
 */
@Controller
public class SiteMapController {

    @RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
    @ResponseBody
    public XmlUrlSet main() {
        XmlUrlSet xmlUrlSet = new XmlUrlSet();
        create(xmlUrlSet, "", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/auth", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/profile", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/question/add", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/help/list", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/question/list", XmlUrl.Priority.HIGH);
        create(xmlUrlSet, "/tag/list", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/article/list", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/article/list", XmlUrl.Priority.MEDIUM);

        // for loop to generate all the links by querying against database

        return xmlUrlSet;
    }

    private void create(XmlUrlSet xmlUrlSet, String link, XmlUrl.Priority priority) {
        xmlUrlSet.addUrl(new XmlUrl("http://www.query.uz" + link, priority));
    }

}