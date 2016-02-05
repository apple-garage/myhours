package com.ibm.employee.bo;

import java.util.List;

import com.ibm.country.model.Country;
import com.ibm.employee.model.Employee;

public interface EmployeeBo {
	
	void save(Employee employee);
	
	void update(Employee employee);
	
	void delete(Employee employee);
	
	void create(Employee employee);
	
	Employee findById(String id);

	List<Employee> findAll();

	Employee getEmployee(String id, String name, Country country, String sector, String jrss);
}
