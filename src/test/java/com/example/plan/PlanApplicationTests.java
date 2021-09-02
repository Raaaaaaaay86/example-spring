package com.example.plan;

import com.example.plan.entity.Course;
import com.example.plan.entity.Info;
import com.example.plan.entity.Student;
import com.example.plan.entity.StudentWithCourse;
import com.example.plan.repository.StudentRepository;
import com.example.plan.service.IStudentWithCourseService;
import com.example.plan.service.Impl.CourseService;
import com.example.plan.service.Impl.StudentService;
import com.example.plan.service.Impl.StudentWithCourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PlanApplicationTests {

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentWithCourseService studentWithCourseService;

	@Test
	public void addCourse() {
		var course = new Course();
		course.setName("體育");
		courseService.save(course);
	}

	@Test
	public void addStudent() {
	}

	@Test
	public void updateStudent() {
		var repoStudent = studentService.findById(44).get();
		repoStudent.setName("王花44");
		studentService.updateById(repoStudent);
	}

	@Test
	public void addStudentToCourse() {
		long studentId = 3;
		long courseId = 2;
		var studentWithCourse = new StudentWithCourse();
		studentWithCourse.setStudentId(studentId);
		studentWithCourse.setCourseId(courseId);
		studentWithCourseService.save(studentWithCourse);
	}
}
