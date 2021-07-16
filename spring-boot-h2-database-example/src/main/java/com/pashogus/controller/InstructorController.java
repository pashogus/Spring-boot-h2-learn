package com.pashogus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pashogus.model.Course;
import com.pashogus.model.Instructor;
import com.pashogus.repository.InstructorRepository;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

	@Autowired
    private InstructorRepository instructorRepository;

	//https://www.javaguides.net/2019/08/spring-boot-jpa-hibernate-one-to-many-example-tutorial.html
	//https://dzone.com/articles/introduction-to-spring-data-jpa-part-4-bidirection
   
	
	@GetMapping("/instructors")
    public List < Instructor > getInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity < Instructor > getInstructorById(
        @PathVariable(value = "id") int instructorId) throws ResourceNotFoundException {
        Instructor user = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
        return ResponseEntity.ok().body(user);
    }

    
    /*
     * when using bidirectional one to many database we need to synchronize-bidirectional-entity-associations or else we will get forign key value as null
     * https://vladmihalcea.com/jpa-hibernate-synchronize-bidirectional-entity-associations/
     * addChild and removeChild methods helps in  entity state synchronization - needs to define in Parent
     * 
     * Below is the code which synchronize-bidirectional-entity-associations
     * ---------------------------------------------------------------------
     * Instructor instructornew = new Instructor(instructor.getFirstName(),instructor.getLastName(),instructor.getEmail());
    	
    	for (Course courseloop : instructor.getCourses() )
    	{
    		Course course = new Course();
    		course.setTitle(courseloop.getTitle());
    		instructornew.addcourse(course);
    		
    	}
     * 
     * 
     * Hibernate: insert into instructor (id, created_at, updated_at, email, first_name, last_name) values (null, ?, ?, ?, ?, ?)
2021-03-05 21:04:15.988 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Fri Mar 05 21:04:15 IST 2021]
2021-03-05 21:04:15.989 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Fri Mar 05 21:04:15 IST 2021]
2021-03-05 21:04:15.990 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [shansasi.28@gamil.com]
2021-03-05 21:04:15.990 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [Sumathi ]
2021-03-05 21:04:15.990 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [5] as [VARCHAR] - [Palaninathan]
2021-03-05 21:04:16.026 DEBUG 7468 --- [nio-8080-exec-1] org.hibernate.SQL                        : insert into course (id, created_at, updated_at, instructor_id, title) values (null, ?, ?, ?, ?)
Hibernate: insert into course (id, created_at, updated_at, instructor_id, title) values (null, ?, ?, ?, ?)
2021-03-05 21:04:16.026 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Fri Mar 05 21:04:16 IST 2021]
2021-03-05 21:04:16.027 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Fri Mar 05 21:04:16 IST 2021]
2021-03-05 21:04:16.029 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [353]
2021-03-05 21:04:16.029 TRACE 7468 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [All]

     * 
     * if we use unidirectional, we will get one extra update comment , refer logs in Instructor.class
     * 
     */
    @PostMapping("/instructors")
    public Instructor createUser(  @RequestBody Instructor instructor) {
    	//below two lines are not required as we are already sending data in JSON format
    	Instructor instructornew = new Instructor(instructor.getFirstName(),instructor.getLastName(),instructor.getEmail());
    	
    	for (Course courseloop : instructor.getCourses() )
    	{
    		Course course = new Course();
    		course.setTitle(courseloop.getTitle());
    		instructornew.addcourse(course);
    		
    	}
    	

      return instructorRepository.save(instructornew);
    }

    @PutMapping("/instructors/{id}")
    public ResponseEntity < Instructor > updateUser(
        @PathVariable(value = "id") int instructorId,
     @RequestBody Instructor userDetails) throws ResourceNotFoundException {
        Instructor user = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));
       user.setFirstName(userDetails.getFirstName());
       user.setLastName(userDetails.getLastName());
       user.setEmail(userDetails.getEmail());
        final Instructor updatedUser = instructorRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/instructors/{id}")
    public Map < String, Boolean > deleteUser(
        @PathVariable(value = "id") int instructorId) throws ResourceNotFoundException {
        Instructor instructor = instructorRepository.findById(instructorId)
            .orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + instructorId));

        instructorRepository.delete(instructor);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
