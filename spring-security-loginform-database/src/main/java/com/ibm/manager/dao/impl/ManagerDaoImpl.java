package com.ibm.manager.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.model.Country;
import com.ibm.manager.dao.ManagerDao;
import com.ibm.manager.model.Manager;

@Repository("managerDao")
public class ManagerDaoImpl implements ManagerDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void save(Manager manager){
		sessionFactory.getCurrentSession().save(manager);
	}
	
	@Transactional
	public void update(Manager manager){
		sessionFactory.getCurrentSession().update(manager);
	}
	
	@Transactional
	public void delete(Manager manager){
		sessionFactory.getCurrentSession().delete(manager);
	}

	@Transactional
	public Manager findManagerByName(String name) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from manager where name ='"+name+"'");
		query.addEntity(Manager.class);
		return (Manager) query.uniqueResult();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Manager> findAll() {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from manager order by name");
		query.addEntity(Manager.class);
		return query.list();
	}

}
