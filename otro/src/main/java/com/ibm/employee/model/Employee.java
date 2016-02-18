package com.ibm.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ibm.country.model.Country;
import com.ibm.manager.model.Manager;

@Entity
@Table(name = "employee", catalog = "db_hour_report", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Employee implements java.io.Serializable{
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private String id;
	@Column(name = "NAME", unique = true, nullable = false)
	private String name;
	@ManyToOne
	@JoinColumn(name = "ID_COUNTRY")
	private Country country;
	@Column(name = "SECTOR")
	private String sector;
	@Column(name = "JRSS")
	private String jrss;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_MANAGER")
	private Manager manager;
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Employee(){
	}
	
	public Employee(String id, String name, Country country, String sector, String jrss, Manager manager){
		this.id = id;
		this.name = name;
		this.country = country;
		this.sector = sector;
		this.jrss = jrss;
		this.manager = manager;
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
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getJrss() {
		return jrss;
	}

	public void setJrss(String jrss) {
		this.jrss = jrss;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", country=" + country + ", sector=" + sector + ", jrss="+ jrss + ", manager=" + manager + "]";
	}
	public List<String> getAllNames(){
		
		List list = new ArrayList();
		list.add("hola");
		list.add("que");
		list.add("tal");
		list.add("chau");
		return list;
		
	}

}
