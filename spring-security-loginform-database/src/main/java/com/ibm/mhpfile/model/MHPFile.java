package com.ibm.mhpfile.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mhpfile", catalog = "ad_06ffd90d8bcb8be", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class MHPFile {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	String id;
	

	@Column(name = "date")
	String date;
	public MHPFile(String date) {
		this.date = date;
		
	}
	public MHPFile() {

	}
	public MHPFile(String id, String date) {
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
