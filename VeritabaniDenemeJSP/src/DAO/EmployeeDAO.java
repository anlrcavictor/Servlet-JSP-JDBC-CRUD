package DAO;

import java.util.List;

import model.Employee;

public interface EmployeeDAO {

	public void insertEmployee(String name,String surname, int salary);
	//
	public List<Employee> getEmployees();	
	//
	public void removeEmployee(int id);
	//
	public Employee getEmployee(int id);
	//
	public void updateEmployee(int id,String name,String surname, int salary);
	
}
