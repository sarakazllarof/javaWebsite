package com.sda.java11.coursecenter.controllers;

import com.sda.java11.coursecenter.dto.courses.CourseWrite;
import com.sda.java11.coursecenter.dto.subscribe.SubscriptionWrite;
import com.sda.java11.coursecenter.entities.Course;
import com.sda.java11.coursecenter.entities.Subscription;
import com.sda.java11.coursecenter.services.ICourseService;
import com.sda.java11.coursecenter.services.ISubscriptionService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    @NonNull
    protected ICourseService courseService;

    @NonNull
    protected ISubscriptionService subscriptionService;

    @GetMapping("")
    public String list(ModelMap modelMap) {
        modelMap.put("courses", courseService.getAllCourses());
        return "website/courses-page";
    }

    @GetMapping("detail/{id}")
    public String index(@PathVariable("id") UUID id, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("message", "Course with id not valid!");
            return "redirect:/";
        }
        Course course = courseService.getById(id);
        if (course == null) {
            redirectAttributes.addFlashAttribute("message", String.format("Course with id '%s' not found!", id.toString()));
            return "redirect:/";
        }
        modelMap.addAttribute("model", courseService.getById(id));

        if(!modelMap.containsKey("subscription"))
            modelMap.addAttribute("subscription", new SubscriptionWrite());

        return "website/course-detail-page";
    }

    @PostMapping("/subscribe")
    public RedirectView editCourse(@ModelAttribute @Valid SubscriptionWrite dto, Errors errors, RedirectAttributes modelMap) {
        if (errors.hasErrors()) {
            String error = String.format("Subscription data is not valid: %s",
                    errors.getAllErrors().stream()
                            .map(o -> String.format("%s: %s", ((FieldError) o).getField(), o.getDefaultMessage()))
                            .collect(Collectors.joining(",")));
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("message", error);
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("subscription", dto);
            return new RedirectView("/courses/detail/" + dto.getCourseId().toString());
        }

        Course dbCourse = courseService.getById(dto.getCourseId());

        if (dbCourse == null) {
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("message", "Course not found in database");
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("subscription", dto);
            return new RedirectView("/courses/detail/" + dto.getCourseId().toString());
        }

        Subscription subscription = subscriptionService.save(dto);
        modelMap.addFlashAttribute("message", "Subscription created successfully");
        return new RedirectView("/courses/detail/" + dto.getCourseId().toString());
    }
}
