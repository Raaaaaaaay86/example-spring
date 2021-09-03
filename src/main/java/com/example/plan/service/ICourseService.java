package com.example.plan.service;

import com.example.plan.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
	public Object save (Course course);

	public Optional<Course> findById (long id);

	public List<Course> findAll ();

	public void deleteById (long id);

	public void deleteAll ();

	public boolean updateById (Course course);
}
