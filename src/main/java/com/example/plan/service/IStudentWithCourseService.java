package com.example.plan.service;

import com.example.plan.entity.StudentWithCourse;
import com.example.plan.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface IStudentWithCourseService {
	public Object save (StudentWithCourse studentWithCourse);

	public Optional<StudentWithCourse> findById (long id);

	public List<StudentWithCourse> findAll ();

	public void deleteById (long id);

	public void deleteAll ();
}
