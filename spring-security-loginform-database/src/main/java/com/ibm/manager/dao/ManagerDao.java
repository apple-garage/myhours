package com.ibm.manager.dao;

import java.util.List;

import com.ibm.manager.model.Manager;

public interface ManagerDao {
	
	void save(Manager manager);
	
	void update(Manager manager);
	
	void delete(Manager manager);

	Manager findManagerByName(String name);

	List<Manager> findAll();

}
