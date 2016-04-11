
package com.ibm.work.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.employee.model.Employee;
import com.ibm.holidaycompare.model.HolidayCompare;
import com.ibm.work.dao.WorkDao;
import com.ibm.work.model.Work;



@Repository("workDao")
public class WorkDaoImpl implements WorkDao{
	
//	private EntityManagerFactory em;
	
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
	
	@Override
	public List<HolidayCompare> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate) {
		
		String qtext = ""
		+"SELECT hxe.eid AS ID_EMPLOYEE, hxe.h_wid AS ID_WEEK, hxe.h_desc AS H_DESC, hxe.h_dates AS H_DATES, hxe.H_HOURS AS H_HOURS, IFNULL(t1.wk_hours,0) AS HOURS "
		+"FROM "
		
		+"	(	SELECT distinct(emp.id) as eid, emp.ID_COUNTRY AS cid, t2.wid AS h_wid, t2.h_hours AS h_hours, t2.h_desc AS h_desc, t2.h_dates AS h_dates "
		+"	FROM work AS wk "
		+"	JOIN employee AS emp ON emp.id = wk.id_employee "
		+"	JOIN country AS c ON emp.id_country = c.id "
		+"	JOIN manager AS m ON m.id = emp.id_manager "
			
//			 producto empleados * semanas_holidays
			+"JOIN	"
				+"(	SELECT c.ID AS h_cid, c.country AS h_cname, GROUP_CONCAT(h.holiday ORDER BY h.date ASC SEPARATOR ' / ') AS h_desc, GROUP_CONCAT(h.date ORDER BY h.date ASC SEPARATOR ' / ') AS h_dates, w.id as wid, COUNT(w.num_week)*8 AS h_hours "
					+"FROM holiday AS h "
					+"JOIN country_has_holiday AS ch ON h.id = ch.id_holiday "
					+"JOIN country AS c ON c.id = ch.id_country "
					+"JOIN WEEK AS w ON h.date BETWEEN w.start_date AND w.end_date ";
					
					if(!startDate.equals("0") && endDate.equals("0")){
						qtext+= "WHERE w.num_week BETWEEN startDate AND endDate ";
					}
			qtext+="GROUP BY h_cid, h_cname, wid "
					+") AS t2 ON emp.ID_COUNTRY = t2.h_cid "
			+"ORDER BY cid, eid, wid "
		+") AS hxe "
		
		+"LEFT OUTER JOIN "
		
		+"(	SELECT wk.id_employee as eid, w.id as wid, e.id_country as cid, sum(wk.hours_x_week) as wk_hours "
			+"FROM WORK AS wk "
			+"JOIN employee AS e ON wk.id_employee = e.id "
			+"JOIN WEEK AS w ON wk.id_week = w.id "
			+"JOIN assignment AS a ON a.id = wk.id_assignment "
			+"JOIN country AS c ON c.id = e.id_country "
			+"WHERE a.category LIKE 'Holiday' "
			+"GROUP BY wk.id_employee, wk.id_week "
		+") AS t1 ON hxe.eid = t1.eid AND hxe.cid = t1.cid AND hxe.h_wid = t1.wid "
		
		+"WHERE hxe.h_hours <> t1.wk_hours "
			+"OR t1.wk_hours is null ";

		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(qtext);
//		query.addEntity(HolidayCompare.class.getName());
		
		List list = query.list();
		Iterator it = list.iterator();
		List<HolidayCompare> retlist = new ArrayList<HolidayCompare>();
		while(it.hasNext()){
			
			Object item[] = (Object[]) it.next();
			HolidayCompare hcItem = new HolidayCompare((String)item[0], (String) item[1].toString(), (String)item[2], (String)item[3], (Object) item[4], (Object)item[5]);
			retlist.add( hcItem );
			
		}
		
		return retlist;
	};
	
}