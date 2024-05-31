package com.tkosmulski.amore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HtmlController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println("called");
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String indexHome() {
        return "homeIndex";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String indexRegister() {
        return "registerIndex";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String indexAdmin() {
        return "adminIndex";
    }

    @RequestMapping(value = "/admin/matching", method = RequestMethod.GET)
    public String indexMatching() {
        return "matchingIndex";
    }

    @RequestMapping(value = "/admin/traits", method = RequestMethod.GET)
    public String indexTraits() {
        return "traitsIndex";
    }

    @RequestMapping(value = "/admin/wanted-traits", method = RequestMethod.GET)
    public String indexWantedTraits() {
        return "wantedTraitsIndex";
    }
}
