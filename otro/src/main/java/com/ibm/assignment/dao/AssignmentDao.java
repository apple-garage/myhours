package com.ibm.assignment.dao;

import com.ibm.assignment.model.Assignment;

public interface AssignmentDao {
	
	void save(Assignment assignment);
	
	void update(Assignment assignment);
	
	void delete(Assignment assignment);

	Assignment findAssignmentById(int id);

}
