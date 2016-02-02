package com.ibm.work.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ibm.employee.model.Employee;
import com.ibm.week.model.Week;

@Entity
@Table(name = "work", catalog = "reportes", uniqueConstraints = @UniqueConstraint(columnNames = "ID"))
public class Work implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_EMPLOYEE")
	private Employee employee;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ID_WEEK")
	private Week week;
//	@ManyToOne(cascade = CascadeType.ALL)
	@Column(name="ID_ASIGMENT")
	private int idAsigment;
	@Column(name = "HOURS_X_WEEK")
	private int hoursByWeek;
	
	public Work(){
	}
	
	public Work(Employee idEmployee, Week idWeek, int idAsigment, int hoursByWeek){
//		this.id = id;
		this.employee = idEmployee;
		this.week = idWeek;
		this.idAsigment = idAsigment;
		this.hoursByWeek = hoursByWeek;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public Employee getEmployee() {
		return this.employee;
	}
	
	public void setEmployee(Employee employee){
		this.employee = employee;
	}
	
	public Week getWeek() {
		return this.week;
	}
	
	public void setWeek(Week week){
		this.week = week;
	}
	
	public int getIdAsigment() {
		return this.idAsigment;
	}
	
	public void setIdAsigment(int idAsigment){
		this.idAsigment = idAsigment;
	}
	
	public int getHoursByWeek() {
		return this.hoursByWeek;
	}
	
	public void setHoursByWeek(int hoursByWeek){
		this.hoursByWeek = hoursByWeek;
	}
	
	@Override
	public String toString() {
		return "Work [ID=" + id + ", EMPLOYEE=" + employee + ", WEEK= " + week + ", ASIGMENT= " + idAsigment + ", HOURS_X_WEEK= " + hoursByWeek +"]";
	}

}
