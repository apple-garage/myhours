package com.ibm.fileProcess;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.employee.bo.EmployeeBo;
import com.ibm.employee.model.Employee;
import com.ibm.week.bo.WeekBo;
import com.ibm.week.model.Week;
import com.ibm.work.bo.WorkBo;
import com.ibm.work.model.Work;

public class Parse {
	final static int CELL_NAME = 1;
	final static int CELL_EMPLOYEE_ID = 64;
	final static int CELL_ASIGMENT = 47;
	final static int CELL_WEEK1 = 105; //first column
	
	static WorkBo workBo;
	static WeekBo weekBo;
	static EmployeeBo employeeBo;
	static Week[] weeks = new Week[20];
	
	public Parse(WorkBo aWorkBo, WeekBo aWeekBo, EmployeeBo aEmployeeBo){
		workBo = aWorkBo;
		weekBo = aWeekBo;
		employeeBo = aEmployeeBo;
	}
	
	public static void Prueba(String filePath){
	try{
        FileInputStream file = new FileInputStream(new File(filePath));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(2);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            if(row.getRowNum()==11)
            	processHeader(row);
            if (row.getRowNum()>11 && !isEmpty(row.getCell(CELL_NAME)))
            	processBody(row);
            else{
        		if (row.getRowNum()>11 && isEmpty(row.getCell(CELL_NAME)))
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
        Employee newEmployee = new Employee(row.getCell(CELL_EMPLOYEE_ID).toString(),row.getCell(CELL_NAME).toString());
        employeeBo.create(newEmployee);
        for(int i=0;i<20;i++){
        	Work newWork = new Work(newEmployee,weeks[i],Integer.valueOf(row.getCell(CELL_ASIGMENT).toString()),(int) row.getCell((CELL_WEEK1+i)).getNumericCellValue());	
        	workBo.save(newWork);
        	System.out.println(newWork.toString());
        }
	}

	private static void processHeader(Row row) {
		for(int i=0;i<20;i++){
			Week aWeek = weekBo.findByDate(row.getCell((CELL_WEEK1+i)).toString());
			weeks[i] = aWeek;
		}
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
