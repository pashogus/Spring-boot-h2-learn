package com.pashogus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pashogus.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	 List<Course> findByInstructorId(int instructorId);
	 Optional<Course> findByIdAndInstructorId(int id, int instructorId);
}
