package com.ibm.role.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.role.bo.RoleBo;
import com.ibm.role.dao.RoleDao;
import com.ibm.role.model.Role;

@Service("roleBo")
public class RoleBoImpl implements RoleBo{
	
	@Autowired
	RoleDao roleDao;
	
	public void update(Role role){
		roleDao.update(role);

}
}



