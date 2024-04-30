package com.sda.java11.coursecenter.controllers;


import com.sda.java11.coursecenter.services.ICourseService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
    @NonNull protected ICourseService courseService;

    @GetMapping("")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("courses", courseService.getLatestPublishedCourses());
        modelMap.addAttribute("discounts", courseService.getLatestCoursesInDiscount());
        return "website/home-page";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}

