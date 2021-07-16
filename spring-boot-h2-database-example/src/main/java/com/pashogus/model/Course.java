package com.pashogus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "course")
public class Course extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    /*
     * by default @ManyToOne and @OneToOne associations 
     *  use the FetchType.EAGER strategy which is bad for performance.
     * so use  fetch=FetchType.LAZY
     */

    /**
     *  @JsonIgnore
     * either we need to remove getInstructor method or we need to add @jsonignore while declaring instructor filed,
     *  or else we will end up in error we will not get json response ,
     *   as instructor is getting course through foreign key and internally course is trying to get instructor through getter.
     *    by adding @jsonignore , @jsonignore ignores a field (instructor) both when reading JSON into Java objects and when writing Java objects into JSON.
     */

   
   /*
    * https://vladmihalcea.com/a-beginners-guide-to-jpa-and-hibernate-cascade-types/
    * 
    * 
    * 
    *    CascadeType.ALL is not required for child for one to many database, as child is bound to parent 
    *    and all cascading operation(Operation/Merge/delete/orphan removal) will be taken care by defining cascading in parent
    *    @ManyToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY) is same as  @ManyToOne(fetch=FetchType.LAZY)
    *    
    *    do not use CascadeType.ALL for @ManytoMany relationship as it inherits CascadeType.remove
    */

  /*
   * https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
   * if  @ManyToOne is not defined in child , then it is unidirectional mapping
   * unidirectional mapping will lead to unwanted execution of update queries in backend
   * note: if @joincolumn is not defined , third table will get created.
   * 
   */
    @JsonIgnore
     @ManyToOne(fetch=FetchType.LAZY)
 @JoinColumn(name = "instructor_id")
    private Instructor instructor;

   

	public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

 

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }
    
    @Override
   	public int hashCode() {
   		final int prime = 31;
   		int result = 1;
   		result = prime * result + id;
   		result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
   		result = prime * result + ((title == null) ? 0 : title.hashCode());
   		return result;
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (this == obj)
   			return true;
   		if (obj == null)
   			return false;
   		if (getClass() != obj.getClass())
   			return false;
   		Course other = (Course) obj;
   		if (id != other.id)
   			return false;
   		if (instructor == null) {
   			if (other.instructor != null)
   				return false;
   		} else if (!instructor.equals(other.instructor))
   			return false;
   		if (title == null) {
   			if (other.title != null)
   				return false;
   		} else if (!title.equals(other.title))
   			return false;
   		return true;
   	}

}
