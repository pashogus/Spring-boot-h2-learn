package com.pashogus.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pashogus.model.Student2;
import com.pashogus.repository.StudentRepository2;
//defining the business logic
@Service
public class StudentService2 
{
@Autowired
StudentRepository2 studentRepository2;
//getting all student records
public List<Student2> getAllStudent2() 
{
List<Student2> students = new ArrayList<Student2>();
studentRepository2.findAll().forEach(student -> students.add(student));
return students;
}
//getting a specific record
public Student2 getStudentById2(int id) 
{
	Student2 student = null;
 if(studentRepository2.findById(id).isPresent())
	{
	 student=studentRepository2.findById(id).get();
	}
 return student;
}
public void saveOrUpdate2(Student2 student) 
{
	System.out.println("inside student 2 service");
	System.out.println(student.getAge2());
	System.out.println(student.getEmail2());
	System.out.println(student.getId2());
	System.out.println(student.getName2());
studentRepository2.save(student);
}
//deleting a specific record
public void delete2(int id) 
{
studentRepository2.deleteById(id);
}
}