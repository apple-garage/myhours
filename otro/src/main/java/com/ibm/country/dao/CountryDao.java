package com.ibm.country.dao;

import com.ibm.country.model.Country;

public interface CountryDao {
	
	void save(Country country);
	
	void update(Country country);
	
	void delete(Country country);

	Country findCountryByCountry(String string);
}
