package com.pashogus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Child extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/*not using the below thing, as we are using @MapsId , this id will same as parent table primary Key
	 * 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 * 
	 * as we are Sharing Primary key , instead of creating new foreign key 
	 * https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
	 */
	@Id
	private Long id;
	
	@Column
	private String name;
	
	@JsonIgnore
	@OneToOne(fetch=FetchType.LAZY)
	@MapsId
	@JoinColumn(name="parent_id")
	private Parent parent;
	
	
	public Child()
	{
		
	}
	

	public Child(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Parent getParent() {
		return parent;
	}


	public void setParent(Parent parent) {
		this.parent = parent;
	}

	
	
}
