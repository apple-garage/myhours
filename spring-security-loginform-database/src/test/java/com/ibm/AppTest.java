package com.ibm;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ibm.config.AppConfig;
import com.ibm.role.model.Role;
import com.ibm.user.bo.UserBo;
import com.ibm.user.model.User;

public class AppTest {
	
	public static void main(String[] args) {
		ApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class); ;

		UserBo user = (UserBo) context.getBean("userBo");
		List<User> userList = user.findAll();
		System.out.println("La cantidad de usuarios es: "+userList.size());
			
		JSONArray array = new JSONArray();
		JSONObject jUser;
		JSONObject jRol = null;
		
		for(User aUser : userList){
			jUser = new JSONObject();
			jUser.put("id", aUser.getId());
			jUser.put("user", aUser.getUser());
			jUser.put("name", aUser.getUsername());
			jUser.put("mail", aUser.getMail());
			for(Role aRol : aUser.getRoles()){
				jRol = new JSONObject();
				jRol.put("id",aRol.getId());
				jRol.put("rol",aRol.getRoles());
			}
			jUser.put("rol",jRol);
			array.add(jUser);
		}
		System.out.println(array.toString());
	}
}