package com.example.plan.service.Impl;

import com.example.plan.entity.Student;
import com.example.plan.repository.StudentRepository;
import com.example.plan.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
  @Autowired
  private StudentRepository studentRepository;

  @Override
  public Object save(Student student) {
    studentRepository.save(student);
    return student;
  }

  @Override
  public Optional<Student> findById(long id) {
    return studentRepository.findById(id);
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public void deleteById(long id) {
    studentRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    studentRepository.deleteAll();
  }

  @Override
  public boolean updateById(Student student) {
    Optional<Student> repoStudent = studentRepository.findById(student.getId());
    if (repoStudent.isEmpty()) return false;
    studentRepository.save(student);
    return true;
  }
}
