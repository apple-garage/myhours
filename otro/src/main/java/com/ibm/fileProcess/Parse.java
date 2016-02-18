
package com.ibm.fileProcess;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.assignment.bo.AssignmentBo;
import com.ibm.assignment.model.Assignment;
import com.ibm.country.bo.CountryBo;
import com.ibm.country.model.Country;
import com.ibm.employee.bo.EmployeeBo;
import com.ibm.employee.model.Employee;
import com.ibm.excel.bo.ExcelBo;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.model.Manager;
import com.ibm.week.bo.WeekBo;
import com.ibm.week.model.Week;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.model.Work;
public class Parse {
	final static int CELL_NAME = 1;
	final static int CELL_EMPLOYEE_COUNTRY = 7;
	final static int CELL_SECTOR = 11;
	final static int CELL_EMPLOYEE_ID = 2;
	final static int CELL_JRSS = 40;
	final static int CELL_ASIGMENT = 53;
	final static int CELL_CLIENT_NAME = 18;
	final static int CELL_PROJECT_NAME = 20;
	final static int CELL_PROJECT_COUNTRY = 30;
	final static int CELL_INDUSTRY = 35;
	final static int CELL_CATEGORY = 55;
	final static int CELL_MANAGER = 68;
	final static int CELL_WEEK1 = 109; //first column week
	
	static WorkBo workBo;
	static WeekBo weekBo;
	static EmployeeBo employeeBo;
	static AssignmentBo assignmentBo;
	static ManagerBo managerBo;
	static CountryBo countryBo;
	static ExcelBo excelBo;
	static Boolean flag;
	static Week[] weeks = new Week[20];
	
	

	public Parse(WorkBo aWorkBo, WeekBo aWeekBo, EmployeeBo aEmployeeBo, AssignmentBo anAssignmentBo, 
			ManagerBo aManagerBo, CountryBo aCountryBo,ExcelBo aexcelBo){
		
		workBo = aWorkBo;
		weekBo = aWeekBo;
		employeeBo = aEmployeeBo;
		assignmentBo = anAssignmentBo;
		managerBo = aManagerBo;
		countryBo = aCountryBo;
		excelBo = aexcelBo;
		flag = true;
	}
	
	public static void Prueba(String filePath){
	try{
        FileInputStream file = new FileInputStream(new File(filePath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext() && flag){
            Row row = rowIterator.next();
            if(row.getRowNum()==11)
            {
            	processHeader(row);
            	if(flag)workBo.loadWorkHistory(weeks[0].getEndDate().toString());
            }	
            if (row.getRowNum()>11 && !isEmpty(row.getCell(CELL_NAME)))
            	processBody(row);
            else{
        		if (row.getRowNum()>11 && isEmpty(row.getCell(CELL_NAME) ))
        		break;
            	}
        }
        
        file.close();
    }
    catch (Exception e){
        e.printStackTrace();
    }
	}

	private static void processBody(Row row) {
		
		Country country = countryBo.getCountry(row.getCell(CELL_EMPLOYEE_COUNTRY).toString());
			String[] manager = row.getCell(CELL_MANAGER).toString().split("/");
			Manager newManager = managerBo.getManager(manager[0]);
			Employee newEmployee = employeeBo.getEmployee(row.getCell(CELL_EMPLOYEE_ID).toString(),row.getCell(CELL_NAME).toString(), country, row.getCell(CELL_SECTOR).toString(), 
					row.getCell(CELL_JRSS).toString(),newManager);
			
			country = countryBo.getCountry(row.getCell(CELL_PROJECT_COUNTRY).toString());
			
			
			Assignment newAssignment = assignmentBo.getAssignment(Integer.valueOf(row.getCell(CELL_ASIGMENT).toString()),row.getCell(CELL_PROJECT_NAME).toString(), 
					row.getCell(CELL_CLIENT_NAME).toString(), country, row.getCell(CELL_INDUSTRY).toString(), row.getCell(CELL_CATEGORY).toString());
			
	        for(int i=0;i<20;i++){
	        	Work newWork = new Work(newEmployee,weeks[i],newAssignment,(int) row.getCell((CELL_WEEK1+i)).getNumericCellValue());	
	        	if(newWork.getHoursByWeek()>0)
	        		workBo.save(newWork);
	//        	System.out.println(newWork.toString());
	        }
		
	}

	private static void processHeader(Row row) {
		for(int i=0;i<20;i++){
			Week aWeek = weekBo.findByDate(row.getCell((CELL_WEEK1+i)).toString());
			weeks[i] = aWeek;
		}
		flag = excelBo.getExcel(weeks[0].getEndDate().toString());
	}
	
	private static boolean isEmpty(Cell cell) {
		if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			return true;
		else 
			return false;
	}

//	private Date makeDate(String cellDate) {
//		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
//		try{
//			Date date = (Date) formatter.parse(cellDate);
//			System.out.println(date);
//			return date;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}

}
