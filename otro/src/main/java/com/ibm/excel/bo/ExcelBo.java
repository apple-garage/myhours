package com.ibm.excel.bo;



import com.ibm.excel.model.Excel;

public interface ExcelBo {
	void save(Excel excel);
	
	void update(Excel excel);
	
	void delete(Excel excel);
	
	Boolean getExcel(String date);
	
	Excel findExcelByDate(String date);
}
