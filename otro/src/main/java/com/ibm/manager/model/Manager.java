package com.ibm.manager.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "manager", catalog = "hour_report_db", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Manager implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name = "NAME")
	private String name;
	
	public Manager() {

	}
	
	public Manager(String name) {
		super();
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + "]";
	}	
	
}
