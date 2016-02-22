package com.ibm.role.dao;

import java.util.List;

import com.ibm.role.model.Role;


public interface RoleDao {

	public void update(Role role);
	
	public Role findById(int id);
	
	public List<Role> findAll();
}
