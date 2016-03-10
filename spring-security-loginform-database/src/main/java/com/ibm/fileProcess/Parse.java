package com.ibm.fileProcess;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.assignment.bo.AssignmentBo;
import com.ibm.assignment.model.Assignment;
import com.ibm.country.bo.CountryBo;
import com.ibm.country.model.Country;
import com.ibm.employee.bo.EmployeeBo;
import com.ibm.employee.model.Employee;
import com.ibm.manager.bo.ManagerBo;
import com.ibm.manager.model.Manager;
import com.ibm.mhpfile.bo.MHPFileBo;
import com.ibm.week.bo.WeekBo;
import com.ibm.week.model.Week;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.model.Work;

public class Parse {
	@Autowired
	static WeekBo weekBo;
	@Autowired
	static WorkBo workBo;
	@Autowired
	static EmployeeBo employeeBo;
	@Autowired
	static AssignmentBo assignmentBo;
	@Autowired
	static ManagerBo managerBo;
	@Autowired
	static CountryBo countryBo;
	@Autowired
	MHPFileBo mhpfileBo;
	
	final static int CELL_NAME = 65;
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
	
	static Week[] weeks = new Week[20];
	static MHPFileBo fileBo;
	static Boolean flag;
	
	public static int processFile(String filePath){
	try{
        FileInputStream file = new FileInputStream(new File(filePath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(row.getRowNum()==11)
            	processHeader(row);
            if(flag)
            	workBo.loadWorkHistory(weeks[0].getEndDate().toString());
            if (row.getRowNum()>11 && !isEmpty(row.getCell(1)))
            	processBody(row);
            else{
        		if (row.getRowNum()>11 && isEmpty(row.getCell(1)))
        		break;
            }
        }
        file.close();
    }catch (Exception e){
        e.printStackTrace();
        return 0;
    }
	return 1;
	}

	private static void processBody(Row row) {	
		Country country = countryBo.getCountry(row.getCell(CELL_EMPLOYEE_COUNTRY).toString());
		
		String[] manager = row.getCell(CELL_MANAGER).toString().split("/");
		Manager newManager = managerBo.getManager(manager[0]);
		
		String[] employee =  row.getCell(CELL_NAME).toString().split("/");
		Employee newEmployee = employeeBo.getEmployee(row.getCell(CELL_EMPLOYEE_ID).toString(), employee[0], country, row.getCell(CELL_SECTOR).toString(), 
				row.getCell(CELL_JRSS).toString(), newManager, row.getCell(CELL_NAME).toString());
		
		country = countryBo.getCountry(row.getCell(CELL_PROJECT_COUNTRY).toString());
		Assignment newAssignment = assignmentBo.getAssignment(Integer.valueOf(row.getCell(CELL_ASIGMENT).toString()),row.getCell(CELL_PROJECT_NAME).toString(), 
				row.getCell(CELL_CLIENT_NAME).toString(), country, row.getCell(CELL_INDUSTRY).toString(), row.getCell(CELL_CATEGORY).toString());
		
        for(int i=0;i<20;i++){
        	Work newWork = new Work(newEmployee,weeks[i],newAssignment,(int) row.getCell((CELL_WEEK1+i)).getNumericCellValue());	
        	if(newWork.getHoursByWeek()>0)
        		workBo.save(newWork);
        }
	}

	private static void processHeader(Row row) {
		for(int i=0;i<20;i++){
			Week aWeek = weekBo.findByDate(row.getCell((CELL_WEEK1+i)).toString());
			weeks[i] = aWeek;
		}
		flag = fileBo.getMHPFile(weeks[0].getEndDate().toString());
	}
	
	private static boolean isEmpty(Cell cell) {
		if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
			return true;
		else 
			return false;
	}

}
