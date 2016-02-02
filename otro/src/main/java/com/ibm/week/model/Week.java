package com.ibm.week.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "week", catalog = "reportes")
public class Week implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name="NUM_WEEK")
	private int numWeek;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	
	public Week(){
	}
	
	public Week(int numWeek, Date startDate, Date endDate){
		this.numWeek = numWeek;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumWeek() {
		return numWeek;
	}
	
	public void setNumWeek(int numWeek) {
		this.numWeek = numWeek;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Override
	public String toString() {
		return "Week [ID=" + id + ", NUM_WEEK =" + numWeek + ", START_DATE =" + startDate + ", END_DATE = " + endDate +"]";
	}
	
}
