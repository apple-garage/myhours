package com.ibm.manager.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.dao.ManagerDao;
import com.ibm.manager.model.Manager;

@Service("managerBo")
public class ManagerBoImpl implements ManagerBo{
	
	@Autowired
	ManagerDao managerDao;
	
	public void setManagerDao(ManagerDao managerDao) {
		this.managerDao = managerDao;
	}

	public void save(Manager manager){
		managerDao.save(manager);
	}
	
	public void update(Manager manager){
		managerDao.update(manager);
	}
	
	public void delete(Manager manager){
		managerDao.delete(manager);
	}

	@Override
	public Manager getManager(String name) {
		Manager manager = findManagerByName(name);
		if(manager==null){
			manager = new Manager(name);
			managerDao.save(manager);
		}
		return manager;
	}

	public Manager findManagerByName(String name){
		return managerDao.findManagerByName(name);
	}

}
