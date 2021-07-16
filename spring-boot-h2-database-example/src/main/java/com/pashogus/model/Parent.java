package com.pashogus.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Parent extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column
	String name;
	
	@Column
	String address;
	
	
	/*  Reference for all four types of implemetation 	
	 * https://howtodoinjava.com/hibernate/hibernate-one-to-one-mapping/#4
	 *  Using foreign key association
2. Using join table
3. Using shared primary key
4. @MapsId
	 *           
	 *           @OneToOne 
	 * https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
	 * -------------------------------------------------------
	 * parent
	 * -------
	 * 	@OneToOne(cascade=CascadeType.ALL,mappedBy="parent",fetch = FetchType.LAZY)
	    private Child child;
	 * 
	 * 
	 * Child
	 * -----
     @OneToOne(fetch=FetchType.LAZY)
	@MapsId
	@JoinColumn(name="parent_id")
	private Parent parent;

	 * 
	 * 
	 */
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="parent",fetch = FetchType.LAZY)
	private Child child;
	
	public Parent()
	{
		
	}
	
	public Parent(String name, String address) {
	super();
	this.name = name;
	this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		if (child == null) {
            if (this.child != null) {
                this.child.setParent(null);
            }
        }
        else {
            child.setParent(this);
        }
        this.child = child;
    }
	
	
}
