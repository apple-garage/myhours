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

	public JSONArray getMoreThanFortyJson(List<Work> workList);

	List<Work> findMoreThanForty(Integer idManager, Integer idCountry, String startDate, String endDate);

}