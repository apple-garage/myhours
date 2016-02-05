package com.ibm.assignment.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.assignment.dao.AssignmentDao;
import com.ibm.assignment.model.Assignment;
import com.ibm.util.CustomHibernateDaoSupport;

@Repository("assignmentDao")
public class AssignmentDaoImpl extends CustomHibernateDaoSupport implements AssignmentDao{
	
	public void save(Assignment assignment){
		getHibernateTemplate().save(assignment);
	}
	
	public void update(Assignment assignment){
		getHibernateTemplate().update(assignment);
	}
	
	public void delete(Assignment assignment){
		getHibernateTemplate().delete(assignment);
	}

	@Transactional
	public Assignment findAssignmentById(int id) {
		return (Assignment) getSessionFactory().getCurrentSession().get(Assignment.class, id);
	}

}
