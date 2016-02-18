package com.ibm.assignment.bo;

import com.ibm.assignment.model.Assignment;
import com.ibm.country.model.Country;
import com.ibm.manager.model.Manager;

public interface AssignmentBo {
	
	void save(Assignment assignment);
	
	void update(Assignment assignment);
	
	void delete(Assignment assignment);
	
	Assignment findAssignmentById(int id);

	Assignment getAssignment(int id, String projectName, String clientName, Country country, String industry, String category);

}
