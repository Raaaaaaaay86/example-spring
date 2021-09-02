package com.example.plan.repository;

import com.example.plan.entity.StudentWithCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentWithCourseRepository extends JpaRepository<StudentWithCourse, Long> {
}
