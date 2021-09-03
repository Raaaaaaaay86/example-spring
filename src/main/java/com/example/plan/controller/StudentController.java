package com.example.plan.controller;

import com.example.plan.entity.Student;
import com.example.plan.reponse.DataResponse;
import com.example.plan.service.Impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/add")
	public void addStudent (@RequestBody Map<String, Student> body) {
		var newStudent = body.get("data");
		studentService.save(newStudent);
	}

	@PutMapping("/update")
	public DataResponse updateStudent (@RequestBody Map<String, Student> body) {
		var updateStudent = body.get("data");
		var repoStudent = studentService.findById(updateStudent.getId());

		if (repoStudent.isEmpty()) {
			return new DataResponse(null, "此學生不存在");
		}

		studentService.updateById(updateStudent);
		var msg = "學生ID: " + updateStudent.getId() + " 更新完成";
		return new DataResponse(updateStudent, msg);
	}

	@GetMapping("/get/{id}")
	public DataResponse getStudentById (@PathVariable(name = "id") long id) {
		var repoStudent = studentService.findById(id);

		if (repoStudent.isEmpty()) {
			return new DataResponse(null, "查無此學生");
		}

		Map<String, Object> result = new HashMap<>();
		result.put("id", repoStudent.get().getId());
		result.put("name", repoStudent.get().getName());
		result.put("sex", repoStudent.get().getSex());
		result.put("birthday", repoStudent.get().getBirthday());

		return new DataResponse(result, "操作成功");
	}

	@GetMapping("/getAll")
	public DataResponse getStudentList () {
		List<Student> studentList = studentService.findAll();

		List<HashMap<String, Object>> resultList = studentList.stream().map(student -> {
			var map = new HashMap<String, Object>();
			map.put("id", student.getId());
			map.put("name", student.getName());
			map.put("sex", student.getSex());
			map.put("birthday", student.getBirthday());
			return map;
		}).collect(Collectors.toList());

		return new DataResponse(resultList);
	}

	@DeleteMapping("/delete")
	public DataResponse deleteStudentById (@RequestBody Map<String, Student> body) {
		var student = body.get("data");
		var repoStudent = studentService.findById(student.getId());

		if (repoStudent.isEmpty()) {
			return new DataResponse(null, "此學生不存在");
		}

		studentService.deleteById(student.getId());
		return new DataResponse(null, "刪除成功");
	}
}
