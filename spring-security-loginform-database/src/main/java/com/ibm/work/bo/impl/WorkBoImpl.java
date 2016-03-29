package com.ibm.work.bo.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.dao.WorkDao;
import com.ibm.work.model.Work;

@Service("workBo")
@Transactional
public class WorkBoImpl implements WorkBo{
	
	@Autowired
	WorkDao workDao;
	
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

	public List<Work> findByManager(int idManager) {
		return workDao.findByManager(idManager);
	}
	
	@Override
	public List<Work> findMoreThanForty(Integer idManager, Integer idCountry, String startDate, String endDate) {
		return workDao.findMoreThanForty(idManager, idCountry, startDate, endDate);
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
			jWork.put("proyect", aWork.getAssignment().getProjectName());
			jWork.put("hours", aWork.getHoursByWeek());
			jaWork.add(jWork);
		}
		return jaWork;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getMoreThanFortyJson(List<Work> workList) {
		JSONArray jaWork = new JSONArray();
		JSONArray jaDetail = new JSONArray();
		JSONObject jDetail = new JSONObject();
		JSONObject jWork = new JSONObject();
		String employee = "";
		int week = 0;
		int totalHours = 0;
		for(Work aWork:workList){
			jDetail = new JSONObject();
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
		return jaWork;
	}
	
}
