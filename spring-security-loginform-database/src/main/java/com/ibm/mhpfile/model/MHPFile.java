package com.ibm.mhpfile.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mhpfile", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class MHPFile {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	String id;
	

	@Column(name = "date")
	Date date;
	public MHPFile(String date) {
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
			Date aDate = formatter.parse(date);
			this.date = aDate;
		}catch(Exception e){ e.printStackTrace();}
		
	}
	public MHPFile() {

	}
	public MHPFile(String id, Date date) {
		super();
		this.id = id;
		this.date = date;
	}
	public Date getFecha() {
		return date;
	}

	public void setFecha(Date fecha) {
		this.date = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
