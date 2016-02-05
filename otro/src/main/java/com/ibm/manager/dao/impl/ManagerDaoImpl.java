package com.ibm.manager.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.manager.dao.ManagerDao;
import com.ibm.manager.model.Manager;
import com.ibm.util.CustomHibernateDaoSupport;

@Repository("managerDao")
public class ManagerDaoImpl extends CustomHibernateDaoSupport implements ManagerDao{
	
	public void save(Manager manager){
		getHibernateTemplate().save(manager);
	}
	
	public void update(Manager manager){
		getHibernateTemplate().update(manager);
	}
	
	public void delete(Manager manager){
		getHibernateTemplate().delete(manager);
	}

	@Transactional
	public Manager findManagerByName(String name) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from manager where name ='"+name+"'");
		query.addEntity(Manager.class);
		return (Manager) query.uniqueResult();
	}

}
