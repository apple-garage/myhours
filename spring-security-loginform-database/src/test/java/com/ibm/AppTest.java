package com.ibm;

import java.util.List;

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
		
		JSONObject obj = new JSONObject();
		
		
		for(User aUser : userList){
			obj.put("id", aUser.getId());
			obj.put("name", aUser.getUsername());
		}
		System.out.println(obj.toString());
	}
}