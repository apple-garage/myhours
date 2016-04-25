package com.ibm.mhpfile.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mhpfile.dao.MHPFileDao;
import com.ibm.mhpfile.model.MHPFile;

@Repository("MHPFileDao")
public class MHPFileDaoImpl implements MHPFileDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(MHPFile MHPFile) {
		sessionFactory.getCurrentSession().save(MHPFile);
		
	}

	@Override
	public void update(MHPFile MHPFile) {
		sessionFactory.getCurrentSession().update(MHPFile);
	}

	@Override
	public void delete(MHPFile MHPFile) {
		sessionFactory.getCurrentSession().delete(MHPFile);
	}

	@Override
	@Transactional
	public MHPFile findMHPFileBydate(String date) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select * from MHPFile where date = str_to_date('"+date+"','%d %b %Y')");
		query.addEntity(MHPFile.class);
		return (MHPFile) query.uniqueResult();
	}


}
