package com.pashogus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




/*
 * 
 * if first creatres it , then it alter the table with foreign key
 * Hibernate: create table course (id integer generated by default as identity, created_at timestamp not null, updated_at timestamp not null, title varchar(255), instructor_id integer, primary key (id))
Hibernate: create table instructor (id integer generated by default as identity, created_at timestamp not null, updated_at timestamp not null, email varchar(255), first_name varchar(255), last_name varchar(255), primary key (id))
Hibernate: alter table course add constraint FKqk2yq2yk124dhlsilomy36qr9 foreign key (instructor_id) references instructor

 * 
 * 
 */

@Entity
@Table(name = "instructor")
public class Instructor extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

	//https://dzone.com/articles/implementing-validation-for-restful-services-with
	//https://stackoverflow.com/questions/61959918/spring-boot-validations-stopped-working-after-upgrade-from-2-2-5-to-2-3-0
	  @NotNull
	  @Size(min=2, message="Name should have atleast 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /*
     * 
     * 
     * WORKING EXAMPLE  this is Unidirectional 
     * --------------------------------------------
     * @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
       @JoinColumn(name = "instructor_id")
      we need to define foreign key constraint id in parent table/class with  @JoinColumn - else  hibernate will create 
      extra table 
      
     * 
     * ******************************************************************************************
     * 
     * Working Example - Bidirectional - three steps
     * ----------------------------------
     *                 Mappedby in parent class and @JoinColumn in child class
     *  1) @OneToMany(cascade = CascadeType.ALL,mappedBy ="instructor",orphanRemoval = true)
        private List < Course > courses= new ArrayList<>();
        
        
        2)  @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name = "instructor_id")
           private Instructor instructor;
     * 
     * 
     *   3) and defining  below two methods in  parent class to synchronize-bidirectional-entity-associations
     *  
     *  
     *  public void addcourse(Course course) {
    	courses.add(course);
    	course.setInstructor(this);
    }
 
    public void removecourse(Course course) {
    	courses.remove(course);
    	course.setInstructor(null);
    }
     *
     * ************************************************************************************************
     */
    
    
    /*https://vladmihalcea.com/orphanremoval-jpa-hibernate/
     * both giving same results , need to know exact use of this orphanRemoval = true
     *  @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
        @OneToMany( cascade = CascadeType.ALL)
     */
   // @OneToMany( cascade = CascadeType.ALL,orphanRemoval = true)
    
    
    /*
     * 
     * Caused by: org.hibernate.AnnotationException: Associations marked as mappedBy must not define database mappings 
     * like @JoinTable or @JoinColumn: com.javatpoint.model.Instructor.courses
     * should not use like this 
     *     @OneToMany(cascade = CascadeType.ALL,mappedBy ="instructor",orphanRemoval = true)
          @JoinColumn(name = "instructor_id")
          
          
          
     */
    @OneToMany(cascade = CascadeType.ALL,mappedBy ="instructor",orphanRemoval = true)
    private List < Course > courses= new ArrayList<>();
/*
 * 
 *   Table with foreign key is child table
 *   table without foreign key is parent table
    
    
 */
     
    
    
    public Instructor() {

    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List < Course > getCourses() {
        return courses;
    }

    public void setCourses(List < Course > courses) {
        this.courses = courses;
    }
    
    public void addcourse(Course course) {
    	courses.add(course);
    	course.setInstructor(this);
    }
 
    public void removecourse(Course course) {
    	courses.remove(course);
    	course.setInstructor(null);
    }
  
}




/*
 * 
 *                               unidirectional one to many database
 *                               -------------------------------
 * parent  - instructor
 * -------
 * @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
  @JoinColumn(name = "instructor_id")
    private List < Course > courses= new ArrayList<>();
    
    child  - course
    -----
       @JsonIgnore
     @ManyToOne(fetch=FetchType.LAZY)
    private Instructor instructor;
    
     
 * Hibernate: insert into instructor (id, created_at, updated_at, email, first_name, last_name) values (null, ?, ?, ?, ?, ?)
2021-03-05 17:02:14.361 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Fri Mar 05 17:02:14 IST 2021]
2021-03-05 17:02:14.361 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Fri Mar 05 17:02:14 IST 2021]
2021-03-05 17:02:14.362 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [shansasi.28@gamil.com]
2021-03-05 17:02:14.362 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [Shanmugasundaram1111342432523567 ]
2021-03-05 17:02:14.362 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [5] as [VARCHAR] - [Palaninathan]
2021-03-05 17:02:14.378 DEBUG 11092 --- [nio-8080-exec-1] org.hibernate.SQL                        : insert into course (id, created_at, updated_at, instructor_id, title) values (null, ?, ?, ?, ?)
Hibernate: insert into course (id, created_at, updated_at, instructor_id, title) values (null, ?, ?, ?, ?)
2021-03-05 17:02:14.379 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Fri Mar 05 17:02:14 IST 2021]
2021-03-05 17:02:14.379 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Fri Mar 05 17:02:14 IST 2021]
2021-03-05 17:02:14.380 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [INTEGER] - [null]
2021-03-05 17:02:14.380 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [java22999942352411134532425]
2021-03-05 17:02:14.428 DEBUG 11092 --- [nio-8080-exec-1] org.hibernate.SQL                        : update course set instructor_id=? where id=?
Hibernate: update course set instructor_id=? where id=?
2021-03-05 17:02:14.429 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [INTEGER] - [225]
2021-03-05 17:02:14.429 TRACE 11092 --- [nio-8080-exec-1] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [INTEGER] - [225]

 * 
 * 
 * 
 * 
 * 
 */
