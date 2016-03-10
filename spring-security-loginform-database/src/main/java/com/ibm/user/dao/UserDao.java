package com.ibm.user.dao;

import java.util.List;

import com.ibm.role.model.Role;
import com.ibm.user.model.User;

public interface UserDao {

	public void save(User user);
	
	public void update (User user);
	
	public void delete (User user);
	
	public User findById(int id);
	
	public List<User> findAll();

	public Role findByRoles(String role);

	public User findByUserName(String username);

	public void deleteById(int id);
}
