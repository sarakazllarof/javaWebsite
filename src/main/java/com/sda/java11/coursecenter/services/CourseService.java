package com.sda.java11.coursecenter.services;

import com.sda.java11.coursecenter.dto.courses.CourseWrite;
import com.sda.java11.coursecenter.entities.Course;
import com.sda.java11.coursecenter.repositories.CourseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    protected @NonNull CourseRepository courseRepository;


    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getLatestPublishedCourses() {
        Page<Course> page = courseRepository.getAllByDeletedFalse(PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "createdAt")));
        return page.getContent();
    }

    @Override
    public List<Course> getLatestCoursesInDiscount() {
        Page<Course> page = courseRepository.findAll(PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "createdAt")));
        return page.getContent();

    }

    @Override
    public String delete(CourseWrite dto) {
        if (dto == null || dto.getId() == null)
            return "Provide a valid course id";

        Optional<Course> dbCourse = courseRepository.findById(dto.getId());
        return dbCourse.map(o ->  {
            courseRepository.delete(o);
            return "Course deleted successfully!";
        }).orElse("Course not present in database!");
    }

    @Override
    public Course getById(UUID id) {
        if (id == null) return null;
        Optional<Course> dbCourse = courseRepository.findById(id);
        return dbCourse.orElse(null);
    }
}
