
package com.ibm.work.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
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
	
	public void loadWorkHistory(String date){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("call load_work_history('"+date+"')");
		query.executeUpdate();
	}

	public List<Work> findByManager(int idManager) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select w.* from work w, employee e where w.id_employee = e.id and e.id_manager = "+idManager+" order by e.name");
		query.addEntity(Work.class);
		return query.list();
	}
	
	@Override
	public List<Work> findMoreThanForty(Integer idManager, Integer idCountry, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Work> findByCountryAndManager(int idCountry, int idManager, String starDate, String endDate) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select w.* from work w, (select w.id_employee,id_week, sum(hours_x_week) "
				+ " as hours from work w, employee e where w.id_employee = e.id and e.id_country = "+idCountry+" and e.id_manager = "+idManager+
				" group by w.id_employee, w.id_week) A where A.id_employee = w.id_employee and A.id_week = w.id_week "
				+ " and A.hours > 30 order by 2, 3");
		query.addEntity(Work.class);
		return query.list();
	}

}