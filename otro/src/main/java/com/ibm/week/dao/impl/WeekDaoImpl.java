package com.ibm.week.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.util.CustomHibernateDaoSupport;
import com.ibm.week.dao.WeekDao;
import com.ibm.week.model.Week;

@Repository("weekDao")
public class WeekDaoImpl extends CustomHibernateDaoSupport implements WeekDao{
	
	public void save(Week week){
		getHibernateTemplate().save(week);
	}
	
	public void update(Week week){
		getHibernateTemplate().update(week);
	}
	
	public void delete(Week week){
		getHibernateTemplate().delete(week);
	}
	
	@Transactional
	public Week findById(int id){
		return (Week) getSessionFactory().getCurrentSession().get(Week.class, id);
	}
	
	@Transactional
	public Week findByDate(String endDate){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from week where END_DATE = str_to_date('"+endDate+"','%d %b %Y')");
		query.addEntity(Week.class) ;
		Week aWeek = (Week) query.uniqueResult();
		return aWeek; 
	}
}
