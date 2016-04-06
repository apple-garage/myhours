package com.ibm.work.dao;

import java.util.List;
import com.ibm.work.model.Work;

public interface WorkDao {
	
	void save(Work work);
	
	void update(Work work);
	
	void delete(Work work);
	
	List<Work> findAll();
	
	void loadWorkHistory(String date);

	List<Work> findByManager(int idManager);

	List<Work> findDiffThanForty(Integer idManager, Integer idCountry, String startDate, String endDate, boolean gt);

	List<Work> findMultipleProjects(Integer idManager, Integer idCountry, String startDate, String endDate);

	List<Work> findNoHolidays(Integer idManager, Integer idCountry, String startDate, String endDate);

}