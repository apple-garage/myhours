package com.ibm.fileProcess;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestBody;

public class CreateCSV {
	final static String[] reports= {"Work_details","More_than_forty","Less_than_forty","Without_holidays","More_one_proyect"};
	
	public static void exportCSV(HttpServletResponse response, @RequestBody String data, int report){
		try{
			response.setContentType("text/csv");
			String reportName = reports[report]+".csv";
			JSONParser jsonParser = new JSONParser();
			response.setHeader("Content-disposition", "attachment;filename="+reportName);
			JSONArray array = new JSONArray();
			array = (JSONArray) jsonParser.parse(data.substring(0, data.lastIndexOf("]")+1));
			switch(report){
			case 0:generateGeneralBody(response, array);
				break;
			case 1:generateMoreOrLessBody(response, array);
				break;
			case 2:generateMoreOrLessBody(response, array);
				break;
			case 3:generateHolidayBody(response, array);
				break;
			case 4:generateMultipleProyectBody(response, array);
				break;
			default:generateGeneralBody(response, array);
			}
			response.getOutputStream().flush();
		}catch(Exception e){e.printStackTrace();}	
	}
	

	private static void generateMultipleProyectBody(HttpServletResponse response, JSONArray array) {
		try{
			JSONObject registry = new JSONObject();
			response.getOutputStream().println("ID|NAME|COUNTRY|MANAGER|WEEK|ASSIGNMENT|TOTAL HOURS");
			for(int i = 0;i<array.size();i++){
				registry = (JSONObject) array.get(i);
				JSONArray details = (JSONArray) registry.get("detail");
				JSONObject jsonDetail = new JSONObject();
				String out = "";
				for(int j=0;j<details.size();j++){
					jsonDetail = (JSONObject) details.get(j);
					out = registry.get("id")+"|"+registry.get("name")+"|"+registry.get("country")+"|"+registry.get("manager")+"|"+registry.get("week")+"|"+jsonDetail.get("assignment");
					
					if(j==0)
						out+="|"+registry.get("totalHours");
					
					response.getOutputStream().println(out);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}


	private static void generateMoreOrLessBody(HttpServletResponse response, JSONArray array) {
	try{
		JSONObject registry = new JSONObject();
		response.getOutputStream().println("ID|NAME|COUNTRY|MANAGER|WEEK|PROJECT|HOURS|TOTAL HOURS");
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			JSONArray details = (JSONArray) registry.get("detail");
			JSONObject jsonDetail = new JSONObject();
			String out = "";
			for(int j=0;j<details.size();j++){
				jsonDetail = (JSONObject) details.get(j);
				out = registry.get("id")+"|"+registry.get("name")+"|"+registry.get("country")+"|"+registry.get("manager")+"|"+registry.get("week")+"|"+jsonDetail.get("assignment")+"|"+jsonDetail.get("hours");
				
				if(j==0)
					out+="|"+registry.get("totalHours");
				
				response.getOutputStream().println(out);
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		}	
		
	}

	private static void generateHolidayBody(HttpServletResponse response, JSONArray array) {
	try{
		JSONObject registry = new JSONObject();
		response.getOutputStream().println("ID|NAME|MANAGER|WEEK|DESCRIPTION|DATES|ACTUAL HOURS|REQUIRED HOURS");
		for(int i = 0;i<array.size();i++){
			registry = (JSONObject) array.get(i);
			response.getOutputStream().println(registry.get("id")+"|"+registry.get("name")+"|"+registry.get("manager")+"|"+registry.get("week")+"|"+registry.get("holiday")+"|"+registry.get("h_dates")+"|"+registry.get("hours")+"|"+registry.get("h_hours"));
		}
	}catch(Exception e){
		e.printStackTrace();
		}	
		
	}

	private static void generateGeneralBody(HttpServletResponse response, JSONArray array) {
		try{
			JSONObject registry = new JSONObject();
			response.getOutputStream().println("ID|NAME|COUNTRY|MANAGER|WEEK|PROJECT|HOURS");
			for(int i = 0;i<array.size();i++){
				registry = (JSONObject) array.get(i);
				response.getOutputStream().println(registry.get("id")+"|"+registry.get("name")+"|"+registry.get("country")+"|"+registry.get("manager")+"|"+registry.get("week")+"|"+registry.get("proyect")+"|"+registry.get("hours"));
			}
		}catch(Exception e){
			e.printStackTrace();
			}	
	}
}
