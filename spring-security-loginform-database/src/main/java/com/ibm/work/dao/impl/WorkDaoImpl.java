package com.ibm.work.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.work.dao.WorkDao;
import com.ibm.work.model.Work;

@Repository("workDao")
public class WorkDaoImpl implements WorkDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Work work){
		sessionFactory.getCurrentSession().save(work);
	}
	
	public void update(Work work){
		sessionFactory.getCurrentSession().update(work);
	}
	
	public void delete(Work work){
		sessionFactory.getCurrentSession().delete(work);
	}
	
	public List<Work> findAll(){
		List<Work> worList = getSessionFactory().getCurrentSession().createCriteria(Work.class).list();
		return worList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}