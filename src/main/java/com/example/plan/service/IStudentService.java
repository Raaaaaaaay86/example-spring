package com.example.plan.service;

import com.example.plan.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
	public Object save (Student student);

	public Optional<Student> findById (long id);

	public List<Student> findAll ();

	public void deleteById (long id);

	public void deleteAll ();

	public Student updateById (Student student);
}
