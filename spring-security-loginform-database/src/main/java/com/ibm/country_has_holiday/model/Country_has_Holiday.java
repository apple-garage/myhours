package com.ibm.country_has_holiday.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ibm.country.model.Country;
import com.ibm.holiday.model.Holiday;

@Entity
@Table(name = "Country_has_Holiday")
public class Country_has_Holiday implements java.io.Serializable{
	@Id
	@ManyToOne
    @JoinColumn(name = "id_country")
    private Country countryh;
	
	@ManyToOne
    @JoinColumn(name = "id_holiday")
    private Holiday holidayh;

	public Country getCountryh() {
		return countryh;
	}

	public void setCountryh(Country country) {
		this.countryh = country;
	}

	public Holiday getHolidayh() {
		return holidayh;
	}

	public void setHolidayh(Holiday holiday) {
		this.holidayh = holiday;
	}
	
	
}
