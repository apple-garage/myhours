package com.ibm.user.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.user.bo.UserBo;
import com.ibm.user.dao.*;
import com.ibm.user.model.User;

@Service("userBo")
	public class UserBoImpl implements UserBo{
		
		@Autowired
		UserDao userDao;
		
		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}

		public void save(User user){
			userDao.save(user);
		}
		
		public void update(User user){
			userDao.update(user);
		}
		
		public void delete(User user){
			userDao.delete(user);
		}
		
		public List<User> findAll(){
			return userDao.findAll();
		}
		
//		public Role findByRoles(String roles){
//			return (Role) getSessionFactory().getCurrentSession().get(Role.class, role);
//}
}

