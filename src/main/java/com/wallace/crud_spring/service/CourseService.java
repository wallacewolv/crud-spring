package com.wallace.crud_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.wallace.crud_spring.model.Course;
import com.wallace.crud_spring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {
  private final CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public List<Course> list() {
    return courseRepository.findAll();
  }

  public Optional<Course> findById(@NotNull @Positive Long id) {
    return courseRepository.findById(id);
  }

  public Course create(@Valid Course course) {
    return courseRepository.save(course);
  }

  public Optional<Course> update(
      @NotNull @Positive Long id,
      @Valid Course course) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          recordFound.setName(course.getName());
          recordFound.setCategory(course.getCategory());

          return courseRepository.save(recordFound);
        });
  }

  public boolean delete(@NotNull @Positive Long id) {
    return courseRepository.findById(id)
        .map(recordFound -> {
          courseRepository.deleteById(id);
          return true;
        })
        .orElse(false);
  }
}
