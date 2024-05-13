package com.my.formatnogotiation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class Welcome {

    @GetMapping("/wel")
    public String getWelcome(){
        return "MyIndex";
    }

    @GetMapping("/wel/{name}")
    public String getWelcome(@PathVariable("name") String name, Model model){
        log.info(name);
        model.addAttribute("welcome","hello " +name);
        return "MyIndex";
    }
}
