package com.ibm.week.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.week.dao.WeekDao;
import com.ibm.week.model.Week;

@Repository("weekDao")
public class WeekDaoImpl implements WeekDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Week week){
		sessionFactory.getCurrentSession().save(week);
	}
	
	public void update(Week week){
		sessionFactory.getCurrentSession().update(week);
	}
	
	public void delete(Week week){
		sessionFactory.getCurrentSession().delete(week);
	}
	
	@Transactional
	public Week findById(int id){
		return (Week) getSessionFactory().getCurrentSession().get(Week.class, id);
	}
	
	@Transactional
	public Week findByDate(String endDate){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from week where END_DATE = str_to_date('"+endDate+"','%d %b %Y')");
		query.addEntity(Week.class);
		Week aWeek = (Week) query.uniqueResult();
		return aWeek; 
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
