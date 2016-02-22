package com.ibm.manager.bo;

import com.ibm.manager.model.Manager;

public interface ManagerBo {
	
	void save(Manager manager);
	
	void update(Manager manager);
	
	void delete(Manager manager);

	Manager getManager(String string);
	
	Manager findManagerByName(String name);

}
