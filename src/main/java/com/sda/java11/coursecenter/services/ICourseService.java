package com.sda.java11.coursecenter.services;

import com.sda.java11.coursecenter.dto.courses.CourseWrite;
import com.sda.java11.coursecenter.entities.Course;

import java.util.List;
import java.util.UUID;

public interface ICourseService {
    Course save(Course course);
    List<Course> getAllCourses();
    List<Course> getLatestPublishedCourses();
    List<Course> getLatestCoursesInDiscount();
    String delete(CourseWrite dto);
    Course getById(UUID id);
}
