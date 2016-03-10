package com.ibm.mhpfile.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.mhpfile.dao.MHPFileDao;
import com.ibm.mhpfile.model.MHPFile;
import com.ibm.util.CustomHibernateDaoSupport;


@Repository("MHPFileDao")
public class MHPFileDaoImpl extends CustomHibernateDaoSupport implements MHPFileDao{

	@Override
	public void save(MHPFile MHPFile) {
		getHibernateTemplate().save(MHPFile);
		
	}

	@Override
	public void update(MHPFile MHPFile) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(MHPFile);
	}

	@Override
	public void delete(MHPFile MHPFile) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(MHPFile);
	}

	@Override
	@Transactional
	public MHPFile findMHPFileBydate(String date) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from MHPFile where date ='"+date+"'");
		query.addEntity(MHPFile.class);
		return (MHPFile) query.uniqueResult();
	}


}
