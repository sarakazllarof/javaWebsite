package com.sda.java11.coursecenter.repositories;

import com.sda.java11.coursecenter.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Page<Course> getAllByDeletedFalse(Pageable pageable);
    Page<Course> getAllByDiscountTrue(Pageable pageable);
}
