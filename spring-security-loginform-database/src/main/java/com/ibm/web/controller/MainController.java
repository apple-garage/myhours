package com.ibm.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.assignment.bo.AssignmentBo;
import com.ibm.country.bo.CountryBo;
import com.ibm.employee.bo.EmployeeBo;
import com.ibm.fileProcess.Parse;
import com.ibm.holiday.bo.HolidayBo;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.week.bo.WeekBo;
import com.ibm.work.bo.WorkBo;

@Controller
public class MainController {
	@Autowired
	WeekBo weekBo;
	@Autowired
	WorkBo workBo;
	@Autowired
	EmployeeBo employeeBo;
	@Autowired
	AssignmentBo assignmentBo;
	@Autowired
	ManagerBo managerBo;
	@Autowired
	CountryBo countryBo;
	@Autowired
	HolidayBo holidayBo;
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file){
		ModelAndView model = new ModelAndView();
		String info;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
//                String filePath = "C://Users/IBM_ADMIN/Desktop/reportes/" + file.getOriginalFilename();
                String filePath = "/Users/apple02/Desktop/reportes/" + file.getOriginalFilename();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath))); 
                stream.write(bytes);
                stream.close();
                Parse parse = new Parse(workBo, weekBo, employeeBo, assignmentBo, managerBo, countryBo);
                parse.Prueba(filePath);
                
                return new ModelAndView("hello", "info","Cargo correctamente"); 
            } catch (Exception e) {
            	return model;
            }
        } else {
        	return new ModelAndView("hello", "info","No se pudo cargar el archivo correctamente");
        }
    }

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

//	@RequestMapping(value ={"/", "/login"},  method = RequestMethod.GET)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
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
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value = { "/test" }, method = RequestMethod.POST)
	public ModelAndView insertHoliday() {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		return model;

	}
	

}