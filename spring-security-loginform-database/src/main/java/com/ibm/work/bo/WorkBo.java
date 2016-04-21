package com.ibm.work.bo;

import java.util.List;

import org.json.simple.JSONArray;

import com.ibm.holidaycompare.model.HolidayCompare;
import com.ibm.work.model.Work;

public interface WorkBo {
	
	void save(Work work);
	
	void update(Work work);
	
	void delete(Work work);
	
	List<Work> findAll();
	
	public void loadWorkHistory(String date);

	List<Work> findByManager(int idManager, int integer, String string, String string2);
	
	List<Work> findDiffThanForty(Integer idManager, Integer idCountry, String startDate, String endDate, boolean gt);
	
	List<Work> findMultipleProjects(Integer idManager, Integer idCountry, String startDate, String endDate);
	
	List<HolidayCompare> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate);

	public JSONArray getJsonWork(List<Work> workList);

	public JSONArray getWeekSummaryJson(List<Work> workList);
	
	public JSONArray getNoHolidaysJson(List<HolidayCompare> hcList);
}