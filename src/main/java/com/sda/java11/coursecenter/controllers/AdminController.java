package com.sda.java11.coursecenter.controllers;

import com.sda.java11.coursecenter.dto.courses.CourseWrite;
import com.sda.java11.coursecenter.dto.subscribe.SubscriptionWrite;
import com.sda.java11.coursecenter.entities.Course;
import com.sda.java11.coursecenter.services.ICourseService;
import com.sda.java11.coursecenter.services.ISubscriptionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    protected @NonNull ICourseService courseService;
    protected @NonNull ISubscriptionService subscriptionService;

    @GetMapping("")
    public String index(ModelMap modelMap) {
        modelMap.put("courses", courseService.getAllCourses());
        return "/admin/courses-page";
    }

    @GetMapping("/subscriptions")
    public String subscriptions(ModelMap modelMap) {
        modelMap.put("subscriptions", subscriptionService.getAllSubscription());
        return "/admin/subscriptions-page";
    }

    @GetMapping("/create-course")
    public String createCourse(ModelMap modelMap) {
        if (!modelMap.containsKey("model")) {
            // Objekti bosh duhet per te mbushur strukturen e formes <form>
            Course model = new Course();

            modelMap.put("model", model);
        }

        return "/admin/create-course-page";
    }

    @PostMapping("/edit-course")
    public RedirectView editCourse(CourseWrite dto, RedirectAttributes modelMap) {
        if (dto != null && dto.getId() != null) {
            Course model = courseService.getById(dto.getId());
            if (model == null) {
                modelMap.addFlashAttribute("message", "The course you are trying to edit is not present in database");
                return new RedirectView("/admin");
            }

            modelMap.addFlashAttribute("model", model);
        }
        else {
            modelMap.addFlashAttribute("message", "To edit a course provide a valid course id");
        }

        return new RedirectView("/admin/create-course");
    }

    @PostMapping("/save-course")
    public RedirectView saveCourse(@ModelAttribute @Valid Course course, Errors errors, RedirectAttributes modelMap) {
        if (errors.hasErrors()) {
            String error = String.format("Object is not valid: %s",
                    errors.getAllErrors().stream()
                            .map(o -> String.format("%s: %s", ((FieldError) o).getField(), o.getDefaultMessage()))
                            .collect(Collectors.joining(",")));
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            modelMap.addFlashAttribute("message", error);
            // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
            //modelMap.addFlashAttribute("model", course);
            return new RedirectView("/admin/create-course");
        }
        courseService.save(course);
        modelMap.addFlashAttribute("message", course.getId() == null ? "Course created successfully" : "Course updated successfully");
        return new RedirectView("/admin");
    }

    @PostMapping("/delete-course")
    public RedirectView deleteCourse(@ModelAttribute CourseWrite dto, RedirectAttributes modelMap) {

        String deleteResult = courseService.delete(dto);
        // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
        modelMap.addFlashAttribute("message", deleteResult);
        return new RedirectView("/admin");
    }

    @PostMapping("/delete-subscription")
    public RedirectView deleteSubscriptio(@ModelAttribute SubscriptionWrite dto, RedirectAttributes modelMap) {

        String deleteResult = subscriptionService.delete(dto);
        // Ky celes eshte i vlefshem vetem per nje kerkes-pergjigje
        modelMap.addFlashAttribute("message", deleteResult);
        return new RedirectView("/admin/subscriptions");
    }
}
