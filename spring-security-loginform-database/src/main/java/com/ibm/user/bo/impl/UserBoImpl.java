package com.ibm.user.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.user.bo.UserBo;
import com.ibm.user.dao.*;
import com.ibm.user.model.User;

@Service("userBo")
@Transactional
	public class UserBoImpl implements UserBo{
		
		@Autowired
		UserDao userDao;
		
		public void setUserDao(UserDao userDao) {
			this.userDao = userDao;
		}

		public void save(User user){
			if(user.getId()==0)
				userDao.save(user);
			else
				userDao.update(user);
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
		
		public User findById(int id){
			return userDao.findById(id);
		}

		@Override
		public void deleteById(int id) {
			userDao.deleteById(id);
		}
		
}

