package com.ibm.country.bo;

import com.ibm.country.model.Country;

public interface CountryBo {

	void save(Country country);
	
	void update(Country country);
	
	void delete(Country country);

	Country getCountry(String string);
	
}
