package com.ibm.holiday.bo.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.model.Country;
import com.ibm.holiday.bo.HolidayBo;
import com.ibm.holiday.dao.HolidayDao;
import com.ibm.holiday.model.Holiday;

@Service("holidayBo")
@Transactional
public class HolidayBoImpl implements HolidayBo {
	
	@Autowired
	HolidayDao holidayDao;
	
	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}

	public void save(Holiday holiday){
		holidayDao.save(holiday);
	}
	
	public void update(Holiday holiday){
		holidayDao.update(holiday);
	}
	
	public void delete(Holiday holiday){
		holidayDao.delete(holiday);
	}
	
	public Holiday findById(int id){
		return holidayDao.findById(id);
	}

	public Holiday findByDate(String date){
		return holidayDao.findByDate(date);
	}
	
	public Set<Holiday> findByCountry(int countryID){
		return holidayDao.findByCountry(countryID);
	}
	
	public Set<Holiday> findByYearandCountry(int countryID,int year){
		return holidayDao.findByYearandCountry(countryID,year);
	}
	
	public void insertHoliday(String holiday, Date date, Set<Country> country){
		holidayDao.insertHoliday(holiday, date, country);
	}

	public void deleteById(int id) {
		holidayDao.deleteById(id);
	}

}
