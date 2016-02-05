package com.ibm.country.daoimpl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.dao.CountryDao;
import com.ibm.country.model.Country;
import com.ibm.util.CustomHibernateDaoSupport;

@Repository("CountryDao")
public class CountryDaoImpl extends CustomHibernateDaoSupport implements CountryDao{

	public void save(Country country){
		getHibernateTemplate().save(country);
	}
	
	public void update(Country country){
		getHibernateTemplate().update(country);
	}
	
	public void delete(Country country){
		getHibernateTemplate().delete(country);
	}

	@Transactional
	public Country findCountryByCountry(String string) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from country where country ='"+string+"'");
		query.addEntity(Country.class);
		return (Country) query.uniqueResult();
	}

}
