package com.example.plan.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Teacher extends AuditEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "姓名不得為空")
  private String name;

  @NotEmpty(message = "性別不得為空")
  private String sex;

  @NotEmpty(message = "生日不得為空")
  private String birthday;
}
