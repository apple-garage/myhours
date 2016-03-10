package com.ibm.holiday.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.ibm.country.model.Country;
import com.ibm.country_has_holiday.model.Country_has_Holiday;


@Entity
@Table(name = "holiday", catalog = "ad_06ffd90d8bcb8be", uniqueConstraints = @UniqueConstraint(columnNames = "ID"))
public class Holiday implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	@Column(name="holiday", unique = true, nullable = false)
	private String holiday;
	@Column(name="date")
	private Date date;
	
	
	@ManyToMany
	@JoinTable(name="country_has_holiday", joinColumns=@JoinColumn(name="id_holiday"), inverseJoinColumns = @JoinColumn(name="id_country"))
	private Set<Country> countries = new HashSet<Country>();
	
	public Holiday(){
		
	}
	
	public Holiday(String holiday, Date date, Set<Country> country){
		
		this.holiday = holiday;
		this.date = date;
		this.countries = country;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Holiday [id=" + id + ", holiday=" + holiday + ", date=" + date + ", country=" + countries + "]";
	}

	public Set<Country> getCountries() {
		return countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
	}
	
}


































