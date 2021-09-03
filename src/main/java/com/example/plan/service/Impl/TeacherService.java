package com.example.plan.service.Impl;

import com.example.plan.entity.Teacher;
import com.example.plan.repository.TeacherRepository;
import com.example.plan.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "teacherService")
public class TeacherService implements ITeacherService {
  @Autowired
  private TeacherRepository teacherRepository;

  @Override
  @Cacheable(value = "teacher", key = "#p0.getId()")
  public Object save(Teacher teacher) {
    teacherRepository.save(teacher);
    return teacher;
  }

  @Override
  @Cacheable(value = "teacher", key = "#p0")
  public Optional<Teacher> findById(long id) {
    return teacherRepository.findById(id);
  }

  @Override
  @Cacheable(value = "teacherList")
  public List<Teacher> findAll() {
    return teacherRepository.findAll();
  }

  @Override
  @CacheEvict(value = "teacher", key = "#p0")
  public void deleteById(long id) {
    teacherRepository.deleteById(id);
  }

  @Override
  @CacheEvict(value = {"teacherList", "teacher"}, allEntries = true)
  public void deleteAll() {
    teacherRepository.deleteAll();
  }

  @Override
  @CachePut(value = "teacher", key = "#p0.getId()")
  public boolean updateById(Teacher teacher) {
    Optional<Teacher> repoTeacher = teacherRepository.findById(teacher.getId());
    if (repoTeacher.isEmpty()) return false;
    teacherRepository.save(teacher);
    return true;
  }
}
