package com.ibm.excel.dao.impl;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.excel.dao.ExcelDao;
import com.ibm.excel.model.Excel;
import com.ibm.util.CustomHibernateDaoSupport;


@Repository("ExcelDao")
public class ExcelDaoImpl extends CustomHibernateDaoSupport implements ExcelDao{

	@Override
	public void save(Excel excel) {
		getHibernateTemplate().save(excel);
		
	}

	@Override
	public void update(Excel excel) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(excel);
	}

	@Override
	public void delete(Excel excel) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(excel);
	}

	@Override
	@Transactional
	public Excel findExcelBydate(String date) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select * from excel where date ='"+date+"'");
		query.addEntity(Excel.class);
		return (Excel) query.uniqueResult();
	}


}
