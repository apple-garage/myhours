package com.ibm.user.bo.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.role.model.Role;
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
		
		@SuppressWarnings("unchecked")
		public JSONObject newJsonUser(User aUser) {
			JSONObject jRol = new JSONObject();
			JSONObject jUser = new JSONObject();
			JSONArray jRoles = new JSONArray();
			
			jUser.put("id", aUser.getId());
			jUser.put("user", aUser.getUser());
			jUser.put("name", aUser.getUsername().substring(aUser.getUsername().lastIndexOf(",")+2,aUser.getUsername().length()));
			jUser.put("lastname", aUser.getUsername().substring(0, aUser.getUsername().lastIndexOf(",")));
			jUser.put("mail", aUser.getMail());
			jUser.put("password", aUser.getUserpassword());
			for(Role aRol : aUser.getRoles()){
				jRol = new JSONObject();
				jRol.put("id",aRol.getId());
				jRol.put("rol",aRol.getRoles());
				jRoles.add(jRol);
			}
			jUser.put("rol",jRoles);
			
			return jUser;
		}
		
}

