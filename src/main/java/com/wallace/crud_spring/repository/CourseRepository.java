package com.wallace.crud_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallace.crud_spring.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
  
}
