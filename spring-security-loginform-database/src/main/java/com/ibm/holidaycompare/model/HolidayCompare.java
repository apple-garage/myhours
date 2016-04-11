package com.ibm.holidaycompare.model;

public class HolidayCompare implements java.io.Serializable{
	
//	@Transient
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="ID_EMPLOYEE")
	private String employee;
	
//	@Transient
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name="ID_WEEK")
	private String week;
	
//	@Transient
//	@Column(name = "H_DESC")
	private String description;
	
//	@Transient
//	@Column(name = "H_DATES")
	private String holidayDates;
	
//	@Transient
//	@Column(name = "HOURS")
	private int hours;
	
//	@Transient
//	@Column(name = "H_HOURS")
	private int holidayHours;
	
//	@Transient
	private String workDetail;
	
	public HolidayCompare(){
		
	}


	public HolidayCompare(String idEmployee, String idWeek, String hDesc, String hDates, Object holidayHoursByWeek, Object hoursByWeek) {
		this.employee = (String) idEmployee;
		this.week = (String) idWeek;
		this.description = (String) hDesc;
		this.holidayDates = (String) hDates;
		this.hours = Integer.parseInt(hoursByWeek.toString());
		this.holidayHours = Integer.parseInt(holidayHoursByWeek.toString());
//		this.workDetail = "";	//	construir jSON Array de Work items que matcheen id_employee y id_week
	}

	public String getEmployee() {
		return employee;
	}


	public void setEmployee(String employee) {
		this.employee = employee;
	}


	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getHolidayDates() {
		return holidayDates;
	}


	public void setHolidayDates(String holidayDates) {
		this.holidayDates = holidayDates;
	}


	public int getHours() {
		return hours;
	}


	public void setHours(int hours) {
		this.hours = hours;
	}


	public int getHolidayHours() {
		return holidayHours;
	}


	public void setHolidayHours(int holidayHours) {
		this.holidayHours = holidayHours;
	}


	public String getWorkDetail() {
		return workDetail;
	}


	public void setWorkDetail(String workDetail) {
		this.workDetail = workDetail;
	}


	@Override
	public String toString() {
		return "HolidayValidation [EMPLOYEE= " + employee + ", WEEK= " + week + ", HOLIDAY_DESC= "+ description + ", HOLIDAY_DATES= " + holidayDates + ", REQUIRED_HOURS= " + holidayHours + ", ACTUAL_HOURS= " + hours +"]";
	}
	
	
}
