package com.ibm.assignment.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.assignment.dao.AssignmentDao;
import com.ibm.assignment.model.Assignment;

@Repository("assignmentDao")
public class AssignmentDaoImpl implements AssignmentDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Assignment assignment){
		sessionFactory.getCurrentSession().save(assignment);
	}
	
	public void update(Assignment assignment){
		sessionFactory.getCurrentSession().update(assignment);
	}
	
	public void delete(Assignment assignment){
		sessionFactory.getCurrentSession().delete(assignment);
	}

	@Transactional
	public Assignment findAssignmentById(int id) {
		return (Assignment) getSessionFactory().getCurrentSession().get(Assignment.class, id);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
