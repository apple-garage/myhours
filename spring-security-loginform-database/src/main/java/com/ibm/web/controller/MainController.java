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
import javax.servlet.http.HttpServletResponse;

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

import com.ibm.assignment.bo.AssignmentBo;
import com.ibm.country.bo.CountryBo;
import com.ibm.country.model.Country;
import com.ibm.employee.bo.EmployeeBo;
import com.ibm.fileProcess.CreateCSV;
import com.ibm.fileProcess.CreatePDF;
import com.ibm.fileProcess.Parse;
import com.ibm.holiday.bo.HolidayBo;
import com.ibm.holiday.model.Holiday;
import com.ibm.holidaycompare.model.HolidayCompare;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.model.Manager;
import com.ibm.mhpfile.bo.MHPFileBo;
import com.ibm.role.bo.RoleBo;
import com.ibm.role.model.Role;
import com.ibm.user.bo.UserBo;
import com.ibm.user.model.User;
import com.ibm.week.bo.WeekBo;
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
	@Autowired
	WeekBo weekBo;
	@Autowired
	EmployeeBo employeeBo;
	@Autowired
	AssignmentBo assignmentBo;
	@Autowired
	MHPFileBo mhpfileBo;

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
                Parse parser = new Parse(mhpfileBo, workBo, countryBo, managerBo, employeeBo, assignmentBo, weekBo);
                parser.processFile(filePath);
                
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
			manager.put("country",aManager.getCountry().getId());
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
	public @ResponseBody String getMyWork(@RequestBody String params) {
		String[] parameters = params.split("&");
		int manager = Integer.parseInt(parameters[0].split("=")[1]);
		
		if(manager==0){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Manager aManager = managerBo.findManagerByName(auth.getName());
			manager = (aManager!=null?aManager.getId():0);
		}
		
 		List<Work> workList = workBo.findByManager(manager, Integer.valueOf(getParameter(parameters[1])), getParameter(parameters[2]), getParameter(parameters[3]));
 		
 		return workBo.getJsonWork(workList).toString();
 		//return workBo.getJsonWork(workList).toString();
	}
	
	@RequestMapping(value = "noholidays", method = RequestMethod.POST )
	public @ResponseBody String getNoHolidays(@RequestBody String params) {
		String[] parameters = params.split("&");
 		List<HolidayCompare> hcList = workBo.findNoHolidays(Integer.valueOf(getParameter(parameters[0])), Integer.valueOf(getParameter(parameters[1])), getParameter(parameters[2]), getParameter(parameters[3]));
 		return workBo.getNoHolidaysJson(hcList).toString();
	}
	
	@RequestMapping(value = "multipleprojects", method = RequestMethod.POST )
	public @ResponseBody String getMultipleProjects(@RequestBody String params) {
		String[] parameters = params.split("&");
 		List<Work> workList = workBo.findMultipleProjects(Integer.valueOf(getParameter(parameters[0])), Integer.valueOf(getParameter(parameters[1])), getParameter(parameters[2]), getParameter(parameters[3]));
 		
 		return workBo.getWeekSummaryJson(workList).toString();
 		//return workBo.getMultipleProjectsJson(workList).toString();
	}
	
	@RequestMapping(value = {"morethanforty","lessthanforty"}, method = RequestMethod.POST )
	public @ResponseBody String getDiffThanForty(@RequestBody String params, HttpServletRequest request) {
		String[] parameters = params.split("&");
		boolean gt = false;
		if(request.getRequestURI().contains("morethanforty")){
			gt = true;
		}
		List<Work> workList = workBo.findDiffThanForty(Integer.valueOf(getParameter(parameters[0])), Integer.valueOf(getParameter(parameters[1])), getParameter(parameters[2]), getParameter(parameters[3]), gt);
		
		return workBo.getWeekSummaryJson(workList).toString();
	}
	
	@RequestMapping(value = "deleteHoliday", method = RequestMethod.POST )
	public @ResponseBody String deleteHoliday(@RequestBody String holiday) {
		holidayBo.deleteById(Integer.valueOf(getParameter(holiday)));
		
		return null;
	}
	
	@RequestMapping(value = "/downloadRequest")
	public void exportFiles(HttpServletResponse response, HttpServletRequest request, @RequestBody String data){
		String[] params = data.split("\r\n");
		String jsondata = params[0].substring(params[0].indexOf("=")+1);
		int report = 0;
		String format = params[1].substring(0,3);
		
		
		if(!("null".equals(params[0].substring(0,params[0].indexOf("=")))))
			report = Integer.valueOf(params[0].substring(0,params[0].indexOf("=")));
		if("csv".equals(format))
			CreateCSV.exportCSV(response, jsondata, report);
		else
			CreatePDF.exportPDF(response, request, jsondata, report);
	}


	private static String getParameter(String string) {
		
		return string.substring(string.lastIndexOf("=")+1,string.length());
	}
}
