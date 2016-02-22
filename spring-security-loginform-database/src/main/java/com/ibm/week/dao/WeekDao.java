package com.ibm.week.dao;

import com.ibm.week.model.Week;

public interface WeekDao {

	void save(Week week);
	
	void update(Week week);
	
	void delete(Week week);
	
	Week findById(int id);
	
	Week findByDate(String endDate);
}
