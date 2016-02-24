package com.ibm.country_has_holiday.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ibm.country.model.Country;
import com.ibm.holiday.model.Holiday;

@Entity
@Table(name = "Country_has_Holiday", catalog = "holiday")
public class Country_has_Holiday {
	@Id
	@ManyToOne
    @JoinColumn(name = "CountryID")
    private Country countryh;
	
	@ManyToOne
    @JoinColumn(name = "HolidayID")
    private Holiday holidayh;

	public Country getCountryh() {
		return countryh;
	}

	public void setCountryh(Country countryh) {
		this.countryh = countryh;
	}

	public Holiday getHolidayh() {
		return holidayh;
	}

	public void setHolidayh(Holiday holidayh) {
		this.holidayh = holidayh;
	}
	
	
}
