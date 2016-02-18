package com.ibm.excel.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.excel.bo.ExcelBo;
import com.ibm.excel.dao.ExcelDao;
import com.ibm.excel.model.Excel;

@Service("ExcelBo")
public class ExcelBoImpl implements ExcelBo{
	@Autowired
	ExcelDao excelDao;


	public void setExcelDao(ExcelDao excelDao) {
		this.excelDao = excelDao;
	}
	@Override
	public void save(Excel excel) {
		// TODO Auto-generated method stub
		excelDao.save(excel);
		
	}

	@Override
	public void update(Excel excel) {
		// TODO Auto-generated method stub
		excelDao.update(excel);
	}

	@Override
	public void delete(Excel excel) {
		// TODO Auto-generated method stub
		excelDao.delete(excel);
	}



	@Override
	public Excel findExcelByDate(String date) {
		
		return excelDao.findExcelBydate(date);
		
	}

	@Override
	public Boolean getExcel(String date) {
		Excel excel = findExcelByDate(date);
		if(excel==null){
			excel = new Excel(date);
			excelDao.save(excel);
			return true;
		}
		return false;
	}
	
}
