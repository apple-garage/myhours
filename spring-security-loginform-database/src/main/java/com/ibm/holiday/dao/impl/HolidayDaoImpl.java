package com.ibm.holiday.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.model.Country;
import com.ibm.holiday.dao.HolidayDao;
import com.ibm.holiday.model.Holiday;
import com.ibm.util.CustomHibernateDaoSupport;

@Repository("holidayDao")
public class HolidayDaoImpl extends CustomHibernateDaoSupport implements HolidayDao{

	public void save(Holiday holiday){
		getHibernateTemplate().save(holiday);
	}
	
	public void update(Holiday holiday){
		getHibernateTemplate().update(holiday);
	}
	
	public void delete(Holiday holiday){
		getHibernateTemplate().delete(holiday);
	}
	
	@Transactional
	public Holiday findById(int id){
		return (Holiday) getSessionFactory().getCurrentSession().get(Holiday.class, id);
	}
	
	@Transactional
	public Holiday findByDate(String date){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from holiday where DATE = str_to_date('"+date+"','%d %b %Y')");
		query.addEntity(Holiday.class);
		Holiday aHoliday = (Holiday) query.uniqueResult();
		return aHoliday; 
	}
	
	@Transactional
	public Set<Holiday> findByCountry(int countryID){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT h.* FROM Holiday AS h JOIN Country_has_Holiday ON Country_has_Holiday.HolidayID = h.HolidayID WHERE Country_has_Holiday.CountryID = "+countryID);
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
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("SELECT DISTINCT country from country, country_has_holiday, holiday where holiday.date = '" + d + "' and Holiday.HolidayID = Country_has_Holiday.HolidayID and Country.CountryID = Country_has_Holiday.CountryID");		
		List<String> aCountry = query.list();
			for(Country c : country){
				if(! aCountry.contains(c.getCountry())){
					Holiday newHoliday = new Holiday(holiday, date, country);
					save(newHoliday);
				}
			}
			
				
	}
}
