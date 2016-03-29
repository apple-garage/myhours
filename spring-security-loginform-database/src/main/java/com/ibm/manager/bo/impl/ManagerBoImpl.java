package com.ibm.manager.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.country.model.Country;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.dao.ManagerDao;
import com.ibm.manager.model.Manager;

@Service("managerBo")
@Transactional
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
	public Manager getManager(String name, Country country) {
		Manager manager = findManagerByName(name);
		if(manager==null){
			manager = new Manager(name,country);
			managerDao.save(manager);
		}
		return manager;
	}

	public Manager findManagerByName(String name){
		return managerDao.findManagerByName(name);
	}

	@Override
	public List<Manager> findAll() {
		return managerDao.findAll();
	}

}
