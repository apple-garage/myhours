package com.ibm.country.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "country", catalog = "db_hour_report", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Country implements java.io.Serializable{
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name = "country", unique = true, nullable = false)
	private String country;
	
	public Country() {
	}
	
	public Country(String country) {
		super();
		this.country = country;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", country=" + country + "]";
	}
	
}
