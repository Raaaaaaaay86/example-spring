package com.example.plan.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Course extends AuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "課程名不得為空")
	private String name;

	@Column(name = "teacher_id")
	@NotNull(message = "教師編號不得為空")
	private long teacherId;

	@ManyToMany(mappedBy = "courseList")
	private List<Student> studentList;
}
