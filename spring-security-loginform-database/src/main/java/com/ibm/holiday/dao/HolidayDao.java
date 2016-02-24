package com.ibm.holiday.dao;

import java.util.Date;
import java.util.Set;

import com.ibm.country.model.Country;
import com.ibm.holiday.model.Holiday;

public interface HolidayDao {

	void save(Holiday holiday);
	
	void update(Holiday holiday);
	
	void delete(Holiday holiday);
	
	Holiday findById(int id);
	
	Holiday findByDate(String date);
	
	Set<Holiday> findByCountry(int CountryID);
	
	void insertHoliday(String holiday, Date date, Set<Country> country);
	
}

