package com.pashogus.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pashogus.model.Student;
import com.pashogus.model.Student2;
import com.pashogus.service.StudentService;
import com.pashogus.service.StudentService2;
//creating RestController
@RestController
public class StudentController 
{
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
//autowired the StudentService class
@Autowired
StudentService studentService;

@Autowired
StudentService2 studentService2;

//creating a get mapping that retrieves all the students detail from the database 
@GetMapping("/student")
private List<Student> getAllStudent() 
{
return studentService.getAllStudent();
}
//creating a get mapping that retrieves the detail of a specific student
@GetMapping("/student/{id}")
private Student getStudent(@PathVariable("id") int id) 
{
return studentService.getStudentById(id);
}
//creating a delete mapping that deletes a specific student
@DeleteMapping("/student/{id}")
private void deleteStudent(@PathVariable("id") int id) 
{
studentService.delete(id);
}
//creating post mapping that post the student detail in the database
@PostMapping("/student")
private int saveStudent(@RequestBody Student student) 
{
	logger.info("inside student 1 controller");
studentService.saveOrUpdate(student);
return student.getId();
}




@GetMapping("/student2")
private List<Student2> getAllStudent2() 
{
return studentService2.getAllStudent2();
}
//creating a get mapping that retrieves the detail of a specific student
@GetMapping("/student2/{id}")
private Student2 getStudent2(@PathVariable("id") int id) 
{
return studentService2.getStudentById2(id);
}
//creating a delete mapping that deletes a specific student
@DeleteMapping("/student2/{id}")
private void deleteStudent2(@PathVariable("id") int id) 
{
studentService2.delete2(id);
}
//creating post mapping that post the student detail in the database
@PostMapping("/student2")
private int saveStudent2(@RequestBody Student2 student2) 
{
	System.out.println("inside student 2 controller");
studentService2.saveOrUpdate2(student2);
return student2.getId2();
}
}
