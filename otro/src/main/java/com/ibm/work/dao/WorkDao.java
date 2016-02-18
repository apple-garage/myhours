package com.ibm.work.dao;

import java.util.Date;
import java.util.List;

import com.ibm.work.model.Work;

public interface WorkDao {
	
	void save(Work work);
	
	void update(Work work);
	
	void delete(Work work);
	
	void loadWorkHistory(String date);
	
	List<Work> findAll();

}