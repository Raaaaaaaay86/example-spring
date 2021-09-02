package com.example.plan.service.Impl;

import com.example.plan.entity.Teacher;
import com.example.plan.repository.TeacherRepository;
import com.example.plan.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {
  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  public Object save(Teacher teacher) {
    teacherRepository.save(teacher);
    return teacher;
  }

  @Override
  public Optional<Teacher> findById(long id) {
    return teacherRepository.findById(id);
  }

  @Override
  public List<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  @Override
  public void deleteById(long id) {
    teacherRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    teacherRepository.deleteAll();
  }

  @Override
  public boolean updateById(Teacher teacher) {
    Optional<Teacher> repoTeacher = teacherRepository.findById(teacher.getId());
    if (repoTeacher.isEmpty()) return false;
    teacherRepository.save(teacher);
    return true;
  }
}
