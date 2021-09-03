package com.example.plan.controller;

import com.example.plan.entity.Course;
import com.example.plan.reponse.DataResponse;
import com.example.plan.service.Impl.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/add")
	public DataResponse addCourse (@RequestBody Map<String, Course> body) {
		var newCourse = body.get("data");
		courseService.save(newCourse);
		return new DataResponse(null, "操作成功");
	}

	@PutMapping("/update")
	public DataResponse updateCourse (@RequestBody Map<String, Course> body) {
		var updateCourse = body.get("data");
		var repoCourse = courseService.findById(updateCourse.getId());

		if (repoCourse.isEmpty()) {
			return new DataResponse(null, "此課程不存在");
		}

		courseService.updateById(updateCourse);

		return new DataResponse(updateCourse, "更新成功");
	}

	@GetMapping("/getAll")
	public DataResponse getCourseList () {
		List<Course> courseList = courseService.findAll();

		List<Map<String, Object>> resultList = courseList.stream().map(course -> {
			var map = new HashMap<String, Object>();
			map.put("id", course.getId());
			map.put("name", course.getName());
			map.put("teacher_id", course.getTeacherId());
			return map;
		}).collect(Collectors.toList());

		return new DataResponse(resultList);
	}

	@DeleteMapping("/delete")
	public DataResponse deleteCourseById (@RequestBody Map<String, Course> body) {
		var deleteCourse = body.get("data");
		var repoCourse = courseService.findById(deleteCourse.getId());

		if (repoCourse.isEmpty()) {
			return new DataResponse(null, "無此課程");
		}

		courseService.deleteById(deleteCourse.getId());
		return new DataResponse(null, "刪除成功");
	}
}
