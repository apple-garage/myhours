package com.ibm.role.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.role.dao.RoleDao;
import com.ibm.role.model.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void update(Role role){
		sessionFactory.getCurrentSession().update(role);
	}

	@Transactional
	public Role findById(int id){
		return (Role) getSessionFactory().getCurrentSession().get(Role.class, id);
	}

	@Transactional
	public List<Role> findAll(){
		List<Role> roleList = getSessionFactory().getCurrentSession().createCriteria(Role.class).list();
		return roleList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role getRolByRole(String parameter) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from roles where roles ='"+parameter+"'");
		query.addEntity(Role.class);
		Role aRol = (Role) query.uniqueResult();
		return aRol; 
	}

}
