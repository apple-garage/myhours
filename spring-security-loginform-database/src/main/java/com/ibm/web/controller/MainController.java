package com.ibm.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.fileProcess.Parse;
import com.ibm.role.bo.RoleBo;
import com.ibm.role.model.Role;
import com.ibm.user.bo.UserBo;
import com.ibm.user.model.User;

@Controller
public class MainController {
	@Autowired
	UserBo userBo;
	@Autowired
	RoleBo roleBo;
	
	
	@RequestMapping(value ={"/", "/welcome**"}, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if("anonymousUser".equals(auth.getName()))
			return new ModelAndView("login");
		else
			return new ModelAndView("MyHours");
	}

	@RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file){
		ModelAndView model = new ModelAndView();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
//                String filePath = "C://Users/IBM_ADMIN/Desktop/reportes/" + file.getOriginalFilename();
                String filePath = "/Users/apple02/Desktop/reportes/" + file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath))); 
                stream.write(bytes);
                stream.close();
                Parse.processFile(filePath);
                
                return new ModelAndView("MyHours", "info","Cargo correctamente"); 
            } catch (Exception e) {
            	return model;
            }
        } else {
        	return new ModelAndView("MyHours", "info","No se pudo cargar el archivo correctamente");
        }
    }

	@RequestMapping(value ="/login",  method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		model.setViewName("403");
		return model;

	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "test", method = RequestMethod.POST )
	public @ResponseBody String getSearchResultViaAjax() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<User> userList = userBo.findAll();
		JSONArray array = new JSONArray();
		
		for(User aUser : userList){
			if(!aUser.getUsername().equals(auth.getName())){
				array.add(newJsonUser(aUser));
			}
		}
		
		return array.toString();
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject newJsonUser(User aUser) {
		JSONObject jRol = new JSONObject();
		JSONObject jUser = new JSONObject();
		
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
		}
		jUser.put("rol",jRol);
		
		return jUser;
	}

	@RequestMapping(value ={"newUser","modifUser"}, method = RequestMethod.POST )
	public ModelAndView newUserViaAjax(@RequestBody String user, HttpServletRequest request) {
		try{
			String[] userData = user.split("&");
			Set<Role> roles = new HashSet<Role>();
			User aUser = new User(userData);
				
			for(int i=7;i<userData.length;i++){
				String rol = getParameter(userData[i]);
				roles.add(roleBo.getRolByRol(rol));
			}
			aUser.setRoles(roles);
			if(request.getRequestURI().contains("modifUser"))
				aUser.setId(Integer.valueOf(getParameter(userData[0])));
			userBo.save(aUser);
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("MyHours", "info","Se produjo un error al crear el usuario");
		}
		return new ModelAndView("MyHours", "info","El usuario se genero de manera exitosa");
	}
	
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST )
	public @ResponseBody String deleteUser(@RequestBody String user) {
		userBo.deleteById(Integer.valueOf(getParameter(user)));
		return null;
	}

	private static String getParameter(String string) {
		return string.substring(string.lastIndexOf("=")+1,string.length());
	}
}