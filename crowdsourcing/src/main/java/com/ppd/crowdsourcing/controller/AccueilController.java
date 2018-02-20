package com.ppd.crowdsourcing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccueilController {

    @GetMapping(value = "hello")
    @ResponseBody
    public String accueil(){
        return "Hello CrowdMerde";
    }
}
