package com.ibm.work.bo;

import java.util.List;

import org.json.simple.JSONArray;

import com.ibm.work.model.Work;

public interface WorkBo {
	
	void save(Work work);
	
	void update(Work work);
	
	void delete(Work work);
	
	List<Work> findAll();
	
	public void loadWorkHistory(String date);

	List<Work> findByManager(int idManager);

	public JSONArray getJsonWork(List<Work> workList);

	public JSONArray getWeekSummaryJson(List<Work> workList);

	public JSONArray getNoHolidaysJson(List<Work> workList);
	
	public JSONArray getMultipleProjectsJson(List<Work> workList);
	
	List<Work> findDiffThanForty(Integer idManager, Integer idCountry, String startDate, String endDate, boolean gt);

	List<Work> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate);
	
	List<Work> findMultipleProjects(Integer idManager, Integer idCountry, String startDate, String endDate);

}