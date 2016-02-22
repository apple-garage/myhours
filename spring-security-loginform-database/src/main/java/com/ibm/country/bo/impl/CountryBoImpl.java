package com.ibm.country.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.country.bo.CountryBo;
import com.ibm.country.dao.CountryDao;
import com.ibm.country.model.Country;

@Service("countryBo")
public class CountryBoImpl implements CountryBo{
	
	@Autowired
	CountryDao countryDao;
	
	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void save(Country country){
		countryDao.save(country);
	}
	
	public void update(Country country){
		countryDao.update(country);
	}
	
	public void delete(Country country){
		countryDao.delete(country);
	}

	@Override
	public Country getCountry(String string) {	
		return countryDao.findCountryByCountry(string);
	}

}
