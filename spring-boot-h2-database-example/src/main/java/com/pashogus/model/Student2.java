package com.pashogus.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//mark class as an Entity 
@Entity
//defining class name as Table name
@Table
public class Student2 
{
//mark id as primary key
@Id
//defining id as column name
@Column
private int id2;

//defining name as column name
@Column
private String name2;
//defining age as column name
@Column
private int age2;
//defining email as column name
@Column
private String email2;


public int getId2() {
	return id2;
}
public void setId2(int id2) {
	this.id2 = id2;
}
public String getName2() {
	return name2;
}
public void setName2(String name2) {
	this.name2 = name2;
}
public int getAge2() {
	return age2;
}
public void setAge2(int age2) {
	this.age2 = age2;
}
public String getEmail2() {
	return email2;
}
public void setEmail2(String email2) {
	this.email2 = email2;
}
}