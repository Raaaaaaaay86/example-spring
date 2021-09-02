package com.example.plan.service;

import com.example.plan.entity.Course;
import com.example.plan.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
  public Object save(Teacher teacher);
  public Optional<Teacher> findById(long id);
  public List<Teacher> findAll();
  public void deleteById(long id);
  public void deleteAll();
  public boolean updateById(Teacher teacher);
}
