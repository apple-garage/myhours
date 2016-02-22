package com.ibm.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ibm.country.model.Country;

@Entity
@Table(name = "assignment", catalog = "my_hours_report", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Assignment implements java.io.Serializable{
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name = "PROJECT_NAME")
	private String projectName;
	@Column(name = "CLIENT_NAME")
	private String clientName;
	@ManyToOne
	@JoinColumn(name = "ID_COUNTRY")
	private Country country;
	@Column(name = "INDUSTRY")
	private String industry;
	@Column(name = "CATEGORY")
	private String category;

	
	public Assignment(){
	}
	
	public Assignment(int id, String projectName, String clientName, Country country, String industry, String category) {
		this.id = id;
		this.projectName = projectName;
		this.clientName = clientName;
		this.country = country;
		this.industry = industry;
		this.category = category;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Assignment [id=" + id + ", projectName=" + projectName + ", clientName=" + clientName + ", country="
				+ country + ", industry=" + industry + ", category=" + category +"]";
	}

	
}
