package com.ibm.week.bo;

import com.ibm.week.model.Week;

public interface WeekBo {

	void save(Week week);
	
	void update(Week week);
	
	void delete(Week week);
	
	Week findById(int id);
	
	Week findByDate(String endDate);
}
