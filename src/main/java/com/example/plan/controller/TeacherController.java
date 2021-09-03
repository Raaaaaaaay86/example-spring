package com.example.plan.controller;

import com.example.plan.entity.Teacher;
import com.example.plan.reponse.DataResponse;
import com.example.plan.service.Impl.TeacherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/add")
	public DataResponse addTeacher (@RequestBody Map<String, Teacher> body) {
		var newTeacher = body.get("data");
		teacherService.save(newTeacher);
		return new DataResponse(newTeacher, "操作成功");
	}

	@PutMapping("/update")
	public DataResponse updateTeacher (@RequestBody Map<String, Teacher> body) {
		var updateTeacher = body.get("data");
		var repoTeacher = teacherService.findById(updateTeacher.getId());

		if (repoTeacher.isEmpty()) {
			return new DataResponse(null, "無此教師");
		}

		teacherService.updateById(updateTeacher);
		var msg = "教師ID: " + updateTeacher.getId() + " 更新完成";
		return new DataResponse(updateTeacher, msg);
	}

	@GetMapping("/get/{id}")
	public DataResponse getTeacherById (@PathVariable(value = "id") long id) {
		var repoTeacher = teacherService.findById(id);

		if (repoTeacher.isEmpty()) {
			return new DataResponse(null, "查無此教師");
		}

		Map<String, Object> result = new HashMap<>();
		result.put("id", repoTeacher.get().getId());
		result.put("name", repoTeacher.get().getName());
		result.put("sex", repoTeacher.get().getSex());
		result.put("birthday", repoTeacher.get().getBirthday());

		return new DataResponse(result, "操作成功");
	}

	@GetMapping("/getAll")
	public DataResponse getTeacherList () {
		List<Teacher> teacherList = teacherService.findAll();

		List<HashMap<String, Object>> resultList = teacherList.stream().map(teacher -> {
			var map = new HashMap<String, Object>();
			map.put("id", teacher.getId());
			map.put("name", teacher.getName());
			map.put("sex", teacher.getSex());
			map.put("birthday", teacher.getBirthday());
			return map;
		}).collect(Collectors.toList());

		return new DataResponse(resultList);
	}

	@DeleteMapping("/delete")
	public DataResponse deleteTeacherById (@RequestBody Map<String, Teacher> body) {
		var teacher = body.get("data");
		var repoTeacher = teacherService.findById(teacher.getId());

		if (repoTeacher.isEmpty()) {
			return new DataResponse(null, "此教師不存在");
		}

		teacherService.deleteById(teacher.getId());
		return new DataResponse(null, "刪除成功");
	}
}
