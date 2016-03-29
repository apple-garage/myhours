package com.ibm.manager.bo;

import java.util.List;

import com.ibm.country.model.Country;
import com.ibm.manager.model.Manager;

public interface ManagerBo {
	
	void save(Manager manager);
	
	void update(Manager manager);
	
	void delete(Manager manager);

	Manager getManager(String string, Country country);
	
	Manager findManagerByName(String name);

	List<Manager> findAll();

}
