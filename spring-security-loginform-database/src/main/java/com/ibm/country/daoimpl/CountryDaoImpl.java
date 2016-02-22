package com.ibm.country.daoimpl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.dao.CountryDao;
import com.ibm.country.model.Country;

@Repository("CountryDao")
public class CountryDaoImpl implements CountryDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Country country){
		sessionFactory.getCurrentSession().save(country);
	}
	
	public void update(Country country){
		sessionFactory.getCurrentSession().update(country);
	}
	
	public void delete(Country country){
		sessionFactory.getCurrentSession().delete(country);
	}

	@Transactional
	public Country findCountryByCountry(String string) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from country where country ='"+string+"'");
		query.addEntity(Country.class);
		return (Country) query.uniqueResult();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
