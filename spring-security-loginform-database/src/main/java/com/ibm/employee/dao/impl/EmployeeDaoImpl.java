package com.ibm.employee.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.employee.dao.EmployeeDao;
import com.ibm.employee.model.Employee;

@Repository("EmployeeDao")
public class EmployeeDaoImpl implements EmployeeDao{
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Employee employee){
		sessionFactory.getCurrentSession().save(employee);
	}
	
	public void update(Employee employee){
		sessionFactory.getCurrentSession().update(employee);
	}
	
	public void delete(Employee employee){
		sessionFactory.getCurrentSession().delete(employee);
	}
	
	public void create(Employee employee){
		if(findById(employee.getId())==null){
			sessionFactory.getCurrentSession().save(employee);
		}
	}
	
	public Employee findById(String id){
		Employee employee = (Employee) getSessionFactory().getCurrentSession().get(Employee.class, id);
		return employee;
	}
	
	public List<Employee> findAll(){
		List<Employee> employeeList = getSessionFactory().getCurrentSession().createCriteria(Employee.class).list();
		return employeeList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
