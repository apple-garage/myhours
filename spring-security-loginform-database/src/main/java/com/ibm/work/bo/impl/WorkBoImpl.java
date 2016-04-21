package com.ibm.work.bo.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.employee.dao.EmployeeDao;
import com.ibm.employee.model.Employee;
import com.ibm.holidaycompare.model.HolidayCompare;
import com.ibm.week.dao.WeekDao;
import com.ibm.week.model.Week;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.dao.WorkDao;
import com.ibm.work.model.Work;

@Service("workBo")
@Transactional
public class WorkBoImpl implements WorkBo{
	
	@Autowired
	WorkDao workDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	WeekDao weekDao;
	
	
	public void setWorkDao(WorkDao workDao) {
		this.workDao = workDao;
	}

	public void save(Work work){
		workDao.save(work);
	}
	
	public void update(Work work){
		workDao.update(work);
	}
	
	public void delete(Work work){
		workDao.delete(work);
	}
	
	public List<Work> findAll(){
		return workDao.findAll();
	}
	
	public void loadWorkHistory(String date){
		workDao.loadWorkHistory(date);
	}

	public List<Work> findByManager(int idManager, int idCountry, String startDate, String endDate) {
		return workDao.findByManager(idManager, idCountry, startDate, endDate);
	}
	
	@Override
	public List<Work> findDiffThanForty(Integer idManager, Integer idCountry, String startDate, String endDate, boolean gt) {
		return workDao.findDiffThanForty(idManager, idCountry, startDate, endDate, gt);
	}
	
	@Override
	public List<Work> findMultipleProjects(Integer idManager, Integer idCountry, String startDate, String endDate) {
		return workDao.findMultipleProjects(idManager, idCountry, startDate, endDate);
	}
	
	@Override
	public List<HolidayCompare> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate) {
		return workDao.findNoHolidays(idManager, idCountry, startDate, endDate);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getJsonWork(List<Work> workList) {
		JSONArray jaWork = new JSONArray();
		JSONObject jWork = new JSONObject();
		for(Work aWork:workList){
			jWork = new JSONObject();
			jWork.put("id", aWork.getEmployee().getId());
			jWork.put("name", aWork.getEmployee().getName());
			jWork.put("country", aWork.getEmployee().getCountry().getCountry());
			jWork.put("manager", aWork.getEmployee().getManager().getName());
			jWork.put("week", aWork.getWeek().getEndDate().toString().substring(0, 10));
			jWork.put("project", aWork.getAssignment().getProjectName());
			jWork.put("hours", aWork.getHoursByWeek());
			jaWork.add(jWork);
		}
		return jaWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getWeekSummaryJson(List<Work> workList) {
		JSONArray jaWork = new JSONArray();
		JSONArray jaDetail = new JSONArray();
		JSONObject jDetail = new JSONObject();
		JSONObject jWork = new JSONObject();
		String employee = "";
		int week = 0;
		int totalHours = 0;
		for(Work aWork:workList){
			
			jDetail = new JSONObject();
			
//			Assumes data is ordered by employee and then week. Only loads generic data at first occurrence.
			if(!employee.equals(aWork.getEmployee().getId()) || aWork.getWeek().getId() != week){
				if(employee != ""){
					jWork.put("detail", jaDetail);
					jWork.put("totalHours",totalHours);
					jaWork.add(jWork);
				}
				jWork = new JSONObject();
				jaDetail = new JSONArray();
				totalHours = 0;
				employee = aWork.getEmployee().getId();
				week = aWork.getWeek().getId();
				jWork.put("id", aWork.getEmployee().getId());
				jWork.put("name", aWork.getEmployee().getName());
				jWork.put("country", aWork.getEmployee().getCountry().getCountry());
				jWork.put("manager", aWork.getEmployee().getManager().getName());
				jWork.put("week", aWork.getWeek().getEndDate().toString().substring(0, 10));
				
			 }
			jDetail.put("assignment", aWork.getAssignment().getProjectName());
			jDetail.put("hours", aWork.getHoursByWeek());
			totalHours+=aWork.getHoursByWeek();
			jaDetail.add(jDetail);
			
		}
		
		jWork.put("detail", jaDetail);
		jWork.put("totalHours",totalHours);
		jaWork.add(jWork);
		
		return jaWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getNoHolidaysJson(List<HolidayCompare> hcList) {
		
		JSONObject j;
		JSONArray ja = new JSONArray();
		
		Employee emp = null;
		String empid = ""; //previous employee id
		
		for (HolidayCompare hc : hcList){
			
			j = new JSONObject();
			
			if(empid != hc.getEmployee() ){
				empid = hc.getEmployee();
				emp = employeeDao.findById(empid);
			}
			
			j.put("id", empid);
			j.put("name", emp.getName() );
			j.put("country", emp.getCountry().getCountry());
			j.put("manager", emp.getManager().getName());
			j.put("week", weekDao.findById(Integer.parseInt(hc.getWeek())).getEndDate().toString().substring(0, 10));
			j.put("holiday", hc.getDescription());
			j.put("h_dates", hc.getHolidayDates());
			j.put("h_hours", hc.getHolidayHours());
			j.put("hours", hc.getHours());
//			j.put("detail", hc.getWorkDetail() );
			
			ja.add(j);
		}
		
		
		return ja;
	}

}
