package com.example.plan.service.Impl;

import com.example.plan.entity.Course;
import com.example.plan.repository.CourseRepository;
import com.example.plan.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {
  @Autowired
  private CourseRepository courseRepository;

  @Override
  public Object save(Course course) {
    System.out.println("[CourseService]" + course);
    courseRepository.save(course);
    return course;
  }

  @Override
  public Optional<Course> findById(long id) {
    return courseRepository.findById(id);
  }

  @Override
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  public void deleteById(long id) {
    courseRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    courseRepository.deleteAll();
  }

  @Override
  public boolean updateById(Course course) {
    Optional<Course> repoCourse = courseRepository.findById(course.getId());
    if(repoCourse.isEmpty()) return false;
    courseRepository.save(course);
    return true;
  }
}
