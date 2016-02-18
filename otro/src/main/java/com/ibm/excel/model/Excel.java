package com.ibm.excel.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "excel", catalog = "db_hour_report", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Excel {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	String id;
	

	@Column(name = "date")
	String date;
	public Excel(String date) {
		this.date = date;
		
	}
	public Excel() {

	}
	public Excel(String id, String date) {
		super();
		this.id = id;
		this.date = date;
	}
	public String getFecha() {
		return date;
	}

	public void setFecha(String fecha) {
		this.date = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
