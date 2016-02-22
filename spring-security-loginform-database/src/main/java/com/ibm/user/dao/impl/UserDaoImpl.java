package com.ibm.user.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.role.model.Role;
import com.ibm.user.dao.UserDao;
import com.ibm.user.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user){
		sessionFactory.getCurrentSession().save(user);
	}

	public void update(User user){
		sessionFactory.getCurrentSession().update(user);
	}

	public void delete(User user){
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional
	public User findById(int id){
		return (User) getSessionFactory().getCurrentSession().get(User.class, id);
	}

	@Transactional
	public List<User> findAll(){
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from users");
		query.addEntity(User.class);
		return query.list();
	}
	@Transactional
	public Role findByRoles(String roles){
		return (Role) getSessionFactory().getCurrentSession().get(Role.class, roles);
	}

	@Transactional
	public User findByUserName(String username) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from users where user = '"+ username +"'");
		query.addEntity(User.class);
		User aUser = (User) query.uniqueResult();
		return aUser; 
	} 
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

} 