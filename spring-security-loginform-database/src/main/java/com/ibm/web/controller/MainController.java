package com.ibm.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.country.bo.CountryBo;
import com.ibm.country.model.Country;
import com.ibm.fileProcess.Parse;
import com.ibm.holiday.bo.HolidayBo;
import com.ibm.holiday.model.Holiday;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.model.Manager;
import com.ibm.role.bo.RoleBo;
import com.ibm.role.model.Role;
import com.ibm.user.bo.UserBo;
import com.ibm.user.model.User;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.model.Work;

@Controller
public class MainController {
	@Autowired
	UserBo userBo;
	@Autowired
	RoleBo roleBo;
	@Autowired
	CountryBo countryBo;
	@Autowired
	HolidayBo holidayBo;
	@Autowired
	ManagerBo managerBo;
	@Autowired
	WorkBo workBo;

	//Views
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
                
                return new ModelAndView("MyHours", "info","myhours.messages.fileerror"); 
            } catch (Exception e) {
            	return model;
            }
        } else {
        	return new ModelAndView("MyHours", "info","myhours.messages.fileok");
        }
    }

	@RequestMapping(value ="/login",  method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,@RequestParam(value = "logout", required = false) String logout) {
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
	
	//Parameters
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "countries", method = RequestMethod.POST )
	public @ResponseBody String getCountries() {
		List<Country> countryList = countryBo.findAll();
		JSONArray array = new JSONArray();
		JSONObject jCountry;
		try {
			for(Country aCountry :countryList){
				jCountry= new JSONObject();
				jCountry.put("id",aCountry.getId());;
				jCountry.put("country", new String(aCountry. getCountry().getBytes(),"ISO-8859-1"));
				array.add(jCountry);	
			}
		}catch(UnsupportedEncodingException ex ){
			ex.printStackTrace();
		}

		return array.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "managers", method = RequestMethod.POST )
	public @ResponseBody String getManagers() {
		List<Manager> managerList = managerBo.findAll();
		JSONArray jaManager = new JSONArray(); 
		for(Manager aManager:managerList){
			JSONObject manager = new JSONObject();
			manager.put("id",aManager.getId());
			manager.put("name",aManager.getName());
			jaManager.add(manager);
		}
		return jaManager.toString();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "listUsers", method = RequestMethod.POST )
	public @ResponseBody String getSearchResultViaAjax() {
		List<User> userList = userBo.findAll();
		JSONArray array = new JSONArray();
		
		for(User aUser : userList){
				array.add(userBo.newJsonUser(aUser));
		}
//		exportCsv(array);
		return array.toString();
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "holidays", method = RequestMethod.GET )
	public @ResponseBody String getSearchResult(int seleccionPais,int year) {
		Set<Holiday> holidayList = holidayBo.findByYearandCountry(seleccionPais,year);
		JSONArray array = new JSONArray();
		JSONObject jHoliday;
		try {
			for(Holiday aHoliday :holidayList){
				jHoliday= new JSONObject();
				jHoliday.put("id",aHoliday.getId());
				jHoliday.put("holiday", new String(aHoliday.getHoliday().getBytes(),"ISO-8859-1"));
				array.add(jHoliday);	
			}
			}catch(UnsupportedEncodingException ex ){
				ex.printStackTrace();
			}

			return array.toString();
	}

	//Ajax calls
	@RequestMapping(value ={"newUser","modifUser"}, method = RequestMethod.POST )
	public ModelAndView newUserViaAjax(@RequestBody String user, HttpServletRequest request) {
		try{
			String[] userData = (user.replaceAll("[+]"," ")).split("&");
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
			return new ModelAndView("MyHours", "error","myhours.messages.saveerror");
		}
		return new ModelAndView("MyHours", "info","myhours.messages.usersavedok");
	}
	
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST )
	public @ResponseBody String deleteUser(@RequestBody String user) {
		try{
			userBo.deleteById(Integer.valueOf(getParameter(user)));
		}catch(Exception e){
			e.printStackTrace();
			return "<fmt:message key=\"myhours.messages.userdeleteerror\"/>";
		}
		return "<fmt:message key=\"myhours.messages.userdeleteok\"/>";
	}
	
	@RequestMapping(value ="newHoliday", method = RequestMethod.POST)
	public ModelAndView newHolidayViaAjax(@RequestBody String holiday, HttpServletRequest request) {
		try{
			String[] holidayData = (holiday.replaceAll("\\+"," ")).split("&");
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(getParameter(holidayData[0]));
			Set<Country> countrySet = new HashSet<Country>();
			for(int i=2;i<holidayData.length;i++){
				countrySet.add(countryBo.findById(Integer.valueOf(getParameter(holidayData[i]))));
			}
			Holiday newHoliday = new Holiday(getParameter(holidayData[1]), date, countrySet);
			holidayBo.save(newHoliday);
		}catch(Exception e){
			e.printStackTrace();
			return new ModelAndView("MyHours", "error","myhours.messages.saveerror");
		}
		return new ModelAndView("MyHours", "info","myhours.messages.holidayok");
	}
		
	@RequestMapping(value = "mywork", method = RequestMethod.POST )
	public @ResponseBody String getMyWork() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Manager aManager = managerBo.findManagerByName(auth.getName());
		int manager=5;
		if(aManager!=null)
			manager=aManager.getId();
 		List<Work> workList = workBo.findByManager(manager);
 		return workBo.getJsonWork(workList).toString();
	}
	
	@RequestMapping(value = "moreTanForty", method = RequestMethod.POST )
	public @ResponseBody String getMoreThanForty(@RequestBody String params) {
		int idManager = 5;
		String[] parameters = params.split("&");
		List<Work> workList = workBo.findMoreThanForty(Integer.valueOf(getParameter(parameters[0])), Integer.valueOf(getParameter(parameters[1])), getParameter(parameters[2]), getParameter(parameters[3]));
		return workBo.getMoreThanFortyJson(workList).toString();
	}
	
	@RequestMapping(value = "deleteHoliday", method = RequestMethod.POST )
	public @ResponseBody String deleteHoliday(@RequestBody String holiday) {
		holidayBo.deleteById(Integer.valueOf(getParameter(holiday)));
		return null;
	}
	
	public void exportCsv(JSONArray array){
		for(int i = 0;i<=array.size();i++){
			System.out.println(array.get(i).toString());
		}
	}

	private static String getParameter(String string) {
		return string.substring(string.lastIndexOf("=")+1,string.length());
	}
}
