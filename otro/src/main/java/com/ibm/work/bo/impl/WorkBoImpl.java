package com.ibm.work.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.work.bo.WorkBo;
import com.ibm.work.dao.WorkDao;
import com.ibm.work.model.Work;

@Service("workBo")
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
	
}
