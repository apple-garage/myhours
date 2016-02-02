package com.ibm.employee.dao;

import java.util.List;
import com.ibm.employee.model.Employee;

public interface EmployeeDao {
	
	void save(Employee employee);
	
	void update(Employee employee);
	
	void delete(Employee employee);
	
	void create(Employee employee); //Lo crea solo si no existe
	
	Employee findById(String id);
	
	List<Employee> findAll();
}
