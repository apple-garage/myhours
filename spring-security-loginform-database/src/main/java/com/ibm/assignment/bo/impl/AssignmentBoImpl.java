package com.ibm.assignment.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.assignment.bo.AssignmentBo;
import com.ibm.assignment.dao.AssignmentDao;
import com.ibm.assignment.model.Assignment;
import com.ibm.country.model.Country;

@Service("assignmentBo")
@Transactional
public class AssignmentBoImpl implements AssignmentBo{

	@Autowired
	AssignmentDao assignmentDao;

	public void setAssignmentDao(AssignmentDao assignmentDao) {
		this.assignmentDao = assignmentDao;
	}
	
	public void save(Assignment assignment){
		assignmentDao.save(assignment);
	}
	
	public void update(Assignment assignment){
		assignmentDao.update(assignment);
	}
	
	public void delete(Assignment assignment){
		assignmentDao.delete(assignment);
	}
	
	@Override
	public Assignment findAssignmentById(int id) {
		return assignmentDao.findAssignmentById(id);
	}

	@Override
	public Assignment getAssignment(int id, String projectName, String clientName, Country country, String industry, String category) {
		Assignment anAssignment = findAssignmentById(id);
		if(anAssignment==null){
			anAssignment = new Assignment(id, projectName, clientName, country, industry, category);
		}
		return anAssignment;
	}

}
