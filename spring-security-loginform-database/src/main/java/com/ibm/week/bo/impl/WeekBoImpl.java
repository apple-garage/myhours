package com.ibm.week.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.week.bo.WeekBo;
import com.ibm.week.dao.WeekDao;
import com.ibm.week.model.Week;

@Service("weekBo")
@Transactional
public class WeekBoImpl implements WeekBo{
	
	@Autowired
	WeekDao weekDao;
	
	public void setWeekDao(WeekDao weekDao) {
		this.weekDao = weekDao;
	}

	public void save(Week week){
		weekDao.save(week);
	}
	
	public void update(Week week){
		weekDao.update(week);
	}
	
	public void delete(Week week){
		weekDao.delete(week);
	}
	
	public Week findById(int id){
		return weekDao.findById(id);
	}

	public Week findByDate(String endDate){
		return weekDao.findByDate(endDate);
	}

}
