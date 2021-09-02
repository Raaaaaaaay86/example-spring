package com.example.plan.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_course")
public class StudentWithCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "student_id")
  private long studentId;
  @Column(name = "course_id")
  private long courseId;
}
