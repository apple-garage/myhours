package com.ibm.user.bo;

import java.util.List;

import com.ibm.user.dao.UserDao;
import com.ibm.user.model.User;

public interface UserBo {
	
	public void setUserDao(UserDao userDao);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(User user);
	
	public void deleteById(int id);
	
	public List<User> findAll();
	
	public User findById(int id);
	
//	public Role findByRoles(String role);

}
