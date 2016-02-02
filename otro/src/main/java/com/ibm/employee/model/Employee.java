package com.ibm.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employee", catalog = "reportes", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Employee implements java.io.Serializable{
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private String id;
	@Column(name = "NAME", unique = true, nullable = false)
	private String name;
	
	public Employee(){
	}
	
	public Employee(String id, String name){
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
