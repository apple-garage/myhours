package com.ibm.role.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.role.bo.RoleBo;
import com.ibm.role.dao.RoleDao;
import com.ibm.role.model.Role;

@Service("roleBo")
@Transactional
public class RoleBoImpl implements RoleBo{
	
	@Autowired
	RoleDao roleDao;
	
	public void update(Role role){
		roleDao.update(role);
	}

	@Override
	public Role getRolByRol(String parameter) {
		return roleDao.getRolByRole(parameter);
	}
}



