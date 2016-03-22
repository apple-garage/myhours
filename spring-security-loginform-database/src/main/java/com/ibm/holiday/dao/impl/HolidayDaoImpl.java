package com.ibm.holiday.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.model.Country;
import com.ibm.holiday.dao.HolidayDao;
import com.ibm.holiday.model.Holiday;
import com.ibm.util.CustomHibernateDaoSupport;

@Repository("holidayDao")
public class HolidayDaoImpl implements HolidayDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Holiday holiday){
		sessionFactory.getCurrentSession().save(holiday);
	}
	
	public void update(Holiday holiday){
		sessionFactory.getCurrentSession().update(holiday);
	}
	
	public void delete(Holiday holiday){
		sessionFactory.getCurrentSession().delete(holiday);
	}
	
	@Transactional
	public Holiday findById(int id){
		return (Holiday) sessionFactory.getCurrentSession().get(Holiday.class, id);
	}
	
	@Transactional
	public Holiday findByDate(String date){
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from holiday where DATE = str_to_date('"+date+"','%d %b %Y')");
		query.addEntity(Holiday.class);
		Holiday aHoliday = (Holiday) query.uniqueResult();
		return aHoliday; 
	}
	
	@Transactional
	public Set<Holiday> findByCountry(int countryID){
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT h.* FROM Holiday AS h JOIN Country_has_Holiday ON Country_has_Holiday.id_holiday = h.ID WHERE Country_has_Holiday.id_country = "+countryID);
		query.addEntity(Holiday.class);
		List<Holiday> aHoliday = (List<Holiday>) query.list();
		Set<Holiday> holidaySet = new HashSet<Holiday>(aHoliday);
		return holidaySet;	
	}
	
	@Transactional
	public void insertHoliday(String holiday, Date date, Set<Country> country){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		date = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d = format.format(date.getTime());
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT DISTINCT country from country, country_has_holiday, holiday where holiday.date = '" + d + "' and Holiday.ID = Country_has_Holiday.id_holiday and Country.ID = Country_has_Holiday.id_country");		
		List<String> aCountry = query.list();
			for(Country c : country){
				if(! aCountry.contains(c.getCountry())){
					Holiday newHoliday = new Holiday(holiday, date, country);
					save(newHoliday);
				}
			}
			
				
	}
	
	public void deleteById(int id) {
		this.delete(findById(id));
	}

	@Transactional
	public Set<Holiday> findByYearandCountry(int countryID, int year){
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT h.* FROM Holiday AS h JOIN Country_has_Holiday ON Country_has_Holiday.id_holiday = h.ID WHERE Country_has_Holiday.id_country = "+countryID +" AND ("+ year + "=0 or year(h.date)=" + year + ")" );
		query.addEntity(Holiday.class);
		List<Holiday> aHoliday = (List<Holiday>) query.list();
		Set<Holiday> holidaySet = new HashSet<Holiday>(aHoliday);
		return holidaySet;	
	}
}
