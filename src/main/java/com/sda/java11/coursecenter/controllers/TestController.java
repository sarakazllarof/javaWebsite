package com.sda.java11.coursecenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public String index(ModelMap map) {
        map.put("myMessage", "hello from java");
        return "test";
    }
}
