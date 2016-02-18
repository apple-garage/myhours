package com.ibm.work.bo;

import java.util.Date;
import java.util.List;

import com.ibm.work.model.Work;

public interface WorkBo {
	
	void save(Work work);
	
	void update(Work work);
	
	void delete(Work work);
	
	public void loadWorkHistory(String date);
	
	List<Work> findAll();

}