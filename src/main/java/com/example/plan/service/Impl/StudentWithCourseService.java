package com.example.plan.service.Impl;

import com.example.plan.entity.StudentWithCourse;
import com.example.plan.repository.StudentWithCourseRepository;
import com.example.plan.service.IStudentWithCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentWithCourseService implements IStudentWithCourseService {
	@Autowired
	StudentWithCourseRepository studentWithCourseRepository;

	@Override
	public Object save (StudentWithCourse studentWithCourse) {
		studentWithCourseRepository.save(studentWithCourse);
		return studentWithCourse;
	}

	@Override
	public Optional<StudentWithCourse> findById (long id) {
		return studentWithCourseRepository.findById(id);
	}

	@Override
	public List<StudentWithCourse> findAll () {
		return studentWithCourseRepository.findAll();
	}

	@Override
	public void deleteById (long id) {
		studentWithCourseRepository.deleteById(id);
	}

	@Override
	public void deleteAll () {
		studentWithCourseRepository.deleteAll();
	}
}
