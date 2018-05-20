package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static final String INSERT_EMPLOYEE ="insert into employee(name,surname,salary) values (?,?,?)";
	private static final String ALL_EMPLOYEES = "select * from employee";
	private static final String DELETE_EMPLOYEE ="delete from employee where id=?";
	private static final String UPDATE_EMPLOYEE ="update employee set name=?,surname=?,salary=? where id=?";
	private static final String GET_EMPLOYEE = "select * from employee where id=?";
	
	private DataSource getDataSource(){
		ConnectionManager manager = new ConnectionManager();
		DataSource dataSource = manager.getMySQLDataSource();
		return dataSource;
	}
	
	@Override
	public void insertEmployee(String name, String surname, int salary) {
		DataSource dataSource = getDataSource();
		
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE);
			ps.setString(1, name);
			ps.setString(2, surname);
			ps.setInt(3, salary);
			ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Employee> getEmployees() {
		DataSource dataSource = getDataSource();
		Connection connection;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(ALL_EMPLOYEES);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				int salary = resultSet.getInt("salary");
				
				Employee employee = new Employee(id, name, surname, salary);
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employees;
		
	}

	@Override
	public void removeEmployee(int id) {
		DataSource dataSource = getDataSource();
		
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void updateEmployee(int id,String name,String surname,int salary){
		
		DataSource dataSource = getDataSource();
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE);
			ps.setString(1,name); 
			ps.setString(2,surname);
			ps.setInt(3, salary);
			ps.setInt(4, id);
			ps.execute();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(int id){
		DataSource dataSource = getDataSource();
		Connection connection;
		Employee employee=new Employee();
		
		try {
			connection=dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setSurname(rs.getString("surname"));
				employee.setSalary(rs.getInt("salary"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
}
