package com.ibm.country.bo;

import java.util.List;

import com.ibm.country.model.Country;

public interface CountryBo {

	void save(Country country);
	
	void update(Country country);
	
	void delete(Country country);

	Country getCountry(String string);

	Country findById(int valueOf);

	List<Country> findAll();
	
}
