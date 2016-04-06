
package com.ibm.work.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.holidayvalidation.model.HolidayValidation;
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
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select w.* from work w, employee e where w.id_employee = e.id and e.id_manager = "+idManager+" order by e.id, w.id_week");
		query.addEntity(Work.class);
		return query.list();
	}
	
	
	public List<Work> findByCountryAndManager(int idCountry, int idManager, String starDate, String endDate) {
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery("select w.* from work w, (select w.id_employee,id_week, sum(hours_x_week) "
				+ " as hours from work w, employee e where w.id_employee = e.id and e.id_country = "+idCountry+" and e.id_manager = "+idManager+
				" group by w.id_employee, w.id_week) A where A.id_employee = w.id_employee and A.id_week = w.id_week "
				+ " and A.hours > 30 order by 2, 3");
		query.addEntity(Work.class);
		return query.list();
	}
	
	
	@Override
	public List<Work> findDiffThanForty(Integer idManager, Integer idCountry, String startDate, String endDate, boolean gt) { 
		
		String fromQuery = "SELECT e.id AS a1, w.id AS a2"
			+ " FROM work wk, employee e, week w"
			+ " WHERE wk.id_week = w.id"
			+ " AND wk.id_employee = e.id"
			+ " GROUP BY wk.id_employee, wk.id_week"
			+ " HAVING SUM(wk.hours_x_week) " + (gt?">":"<") + " 40";
		
//		String qtext = "SELECT e.id as eid, e.name as ename, c.country as cname, m.name as manager, w.end_date as wend, wk.hours_x_week as hours, a.project_name as project"
		String qtext = "SELECT wk.*"
				+ " FROM `work` as wk"
				+ " JOIN `week` as w ON wk.id_week = w.id"
				+ " JOIN `employee` as e ON wk.id_employee = e.id"
				+ " JOIN `country` as c ON c.id = e.id_country"
				+ " JOIN `manager` as m ON m.id = e.id_manager"
				+ " JOIN `assignment` as a ON a.id = wk.id_assignment"
				+ " JOIN ( " + fromQuery + ") as A ON A.a1 = wk.id_employee AND A.a2 = wk.id_week";
		
//		Agregar WHERE de `country` y `manager`
		boolean whereSet = false;
		
		if (idCountry != 0){
			qtext += " WHERE e.id_country = '" + idCountry + "'";
			whereSet = true;
		}
		
		if (idManager != 0){
			qtext += (!whereSet?" WHERE":" AND") + " m.id = '" + idManager + "'";
		}
		
		qtext+= " ORDER BY e.id, wk.id_week ";
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(qtext);
		
		query.addEntity(Work.class);
		
		return query.list();
	}
	
	@Override
	public List<Work> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate) { 
		
		String qtext = ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ ""
				+ "";
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(qtext);
		
		query.addEntity(HolidayValidation.class);
		
		return query.list();
	}

	@Override
	public List<Work> findMultipleProjects(Integer idManager, Integer idCountry, String startDate, String endDate) { 
		
		String qtext = "SELECT B.* "
				+ "FROM "
					+ "( SELECT wk.* "
					+ "FROM `work` as wk "
					+ "JOIN `employee` as e ON wk.id_employee = e.id "
					+ "JOIN `week` as w ON w.ID = wk.ID_WEEK "
					+ "JOIN `country` as c ON c.ID = e.ID_COUNTRY "
					+ "JOIN `assignment` as a ON wk.ID_ASSIGNMENT = a.id "
					+ "JOIN `manager` as m ON e.ID_MANAGER = m.ID ";
//		Agregar WHERE de `manager`
		if (idManager != 0){
			qtext += "WHERE m.id = '" + idManager + "' ";
		}
		
		qtext+= ") AS B "; 
		
		qtext+= "JOIN "
					+ "(select e.id as eid, wk.id_week as wid, GROUP_CONCAT(a.category) as these_should_be_productive_only_cats, GROUP_CONCAT(a.project_name) as project_names "
					+ "from `work` as wk, `employee` as e, `assignment` as a "
					+ "where e.ID = wk.ID_EMPLOYEE "
					+ "and wk.ID_ASSIGNMENT = a.ID "
					+ "and a.CATEGORY IN ('Billable', 'Cost Recovery', 'Bid & Proposal', 'Mgmt Decided/Strategic Invest') "
					+ "group by 1,2 "
					+ "having count(distinct(a.id)) > 1) AS A "
				+ "ON A.eid = B.id_employee "
					+ "AND A.wid = B.ID_WEEK ";
		
//		Agregar WHERE de `country`
		if (idCountry != 0){
			qtext += "WHERE B.id_country = '" + idCountry + "'";
		}
		
		qtext+= "ORDER BY B.id_employee, B.id_week ";
		
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(qtext);
		
		query.addEntity(Work.class);
		
		return query.list();
	}
}