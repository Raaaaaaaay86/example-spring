package com.example.plan.service.Impl;

import com.example.plan.entity.Teacher;
import com.example.plan.repository.TeacherRepository;
import com.example.plan.service.ITeacherService;
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
@CacheConfig(cacheNames = "teacherService")
public class TeacherService implements ITeacherService {

	private final Logger logger = Logger.getLogger("stdout1");

	@Autowired
	private TeacherRepository teacherRepository;

	@Override
	@Cacheable(value = "teacher", key = "#p0.getId()")
	public Object save (Teacher teacher) {
		teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	@Cacheable(value = "teacher", key = "#p0")
	public Optional<Teacher> findById (long id) {
		logger.debug("Get teacher ID: " + id + " from SQL.");
		return teacherRepository.findById(id);
	}

	@Override
	@Cacheable(value = "teacherList")
	public List<Teacher> findAll () {
		logger.debug("Get teacher list.");
		return teacherRepository.findAll();
	}

	@Override
	@CacheEvict(value = "teacher", key = "#p0")
	public void deleteById (long id) {
		logger.debug("Delete Teacher ID: " + id + ".");
		teacherRepository.deleteById(id);
	}

	@Override
	@CacheEvict(value = {"teacherList", "teacher"}, allEntries = true)
	public void deleteAll () {
		logger.debug("Delete all teacher.");
		teacherRepository.deleteAll();
	}

	@Override
	@CachePut(value = "teacher", key = "#p0.getId()")
	public boolean updateById (Teacher teacher) {
		Optional<Teacher> repoTeacher = teacherRepository.findById(teacher.getId());
		if (repoTeacher.isEmpty()) {
			return false;
		}
		teacherRepository.save(teacher);
		logger.debug("Update teacher ID: " + repoTeacher.get().getId() + ".");
		return true;
	}
}
