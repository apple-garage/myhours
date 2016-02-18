package com.ibm.excel.dao;



import com.ibm.excel.model.Excel;

public interface ExcelDao {
	void save(Excel excel);
	
	void update(Excel excel);
	
	void delete(Excel excel);

	Excel findExcelBydate(String date);

}
