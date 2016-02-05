package com.ibm.employee.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.country.model.Country;
import com.ibm.employee.bo.EmployeeBo;
import com.ibm.employee.dao.EmployeeDao;
import com.ibm.employee.model.Employee;

@Service("employeeBo")
public class EmployeeBoImpl implements EmployeeBo{
	
	@Autowired
	EmployeeDao employeeDao;
	
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void save(Employee employee){
		employeeDao.save(employee);
	}
	
	public void update(Employee employee){
		employeeDao.update(employee);
	}
	
	public void delete(Employee employee){
		employeeDao.delete(employee);
	}
	
	public void create(Employee employee){
		employeeDao.create(employee);
	}
	
	public Employee findById(String id){
		return employeeDao.findById(id);
	}
	
	public List<Employee> findAll(){
		return employeeDao.findAll();
	}

	@Override
	public Employee getEmployee(String id, String name, Country country, String sector, String jrss) {
		Employee aEmployee = findById(id);
		if(aEmployee==null){
			aEmployee = new Employee(id, name, country, sector, jrss);
			save(aEmployee);
		}
		return aEmployee;
	}
}