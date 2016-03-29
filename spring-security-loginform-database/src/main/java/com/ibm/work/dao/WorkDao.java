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

	List<Work> findMoreThanForty(Integer idManager, Integer idCountry, String startDate, String endDate);

}