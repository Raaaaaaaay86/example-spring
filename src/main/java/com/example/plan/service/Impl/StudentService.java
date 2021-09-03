package com.example.plan.service.Impl;

import com.example.plan.entity.Student;
import com.example.plan.repository.StudentRepository;
import com.example.plan.service.IStudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "studentService")
public class StudentService implements IStudentService {

  private final Logger logger = Logger.getLogger("stdout1");

  @Autowired
  private StudentRepository studentRepository;

  @Override
  @Cacheable(value = "student", key = "#p0.getId()")
  public Object save(Student student) {
    studentRepository.save(student);
    return student;
  }

  @Override
  @Cacheable(value = "student", key = "#p0")
  public Optional<Student> findById(long id) {
    logger.debug("Get Student ID " + id + " From SQL");
    return studentRepository.findById(id);
  }

  @Override
  @Cacheable(value = "studentList")
  public List<Student> findAll() {
    logger.debug("Get student list from SQL");
    return studentRepository.findAll();
  }

  @Override
  @CacheEvict(value = "student", key = "#p0")
  public void deleteById(long id) {
    logger.debug("Delete Student ID: " + id);
    studentRepository.deleteById(id);
  }

  @Override
  @CacheEvict(value = "student", allEntries = true)
  public void deleteAll() {
    logger.debug("Delete all student....");
    studentRepository.deleteAll();
  }

  @Override
  @CachePut(value = "student", key = "#p0.getId()")
  public Student updateById(Student student) {
    Optional<Student> repoStudent = studentRepository.findById(student.getId());
    if (repoStudent.isEmpty()) return null;
    studentRepository.save(student);
    logger.debug("Update student ID: " + student.getId());
    return student;
  }
}
