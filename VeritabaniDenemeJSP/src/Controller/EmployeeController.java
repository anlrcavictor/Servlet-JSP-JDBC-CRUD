package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import model.Employee;

@WebServlet("/employeeController")
public class EmployeeController extends HttpServlet{
	
	private static String INSERT="/employeeUpdate.jsp";
	private static String LIST="/employee.jsp";
	private static String UPDATE="/employeeUpdate2.jsp";
	
	private EmployeeDAO employeeDAO;
	
	public EmployeeController(){
		super();
		employeeDAO=new EmployeeDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		String forward="";
		String action=req.getParameter("action");
		if(action==null){
			
			List<Employee> employeeList = employeeDAO.getEmployees();
			//
			req.setAttribute("allEmployees", employeeList);
			forward=LIST;
		}
		else if(action.equalsIgnoreCase("insert")){
			forward=INSERT;
		}
		 else if(action.equalsIgnoreCase("delete")){
			int id = Integer.parseInt(req.getParameter("employeeId"));
			
			employeeDAO.removeEmployee(id);
			forward=LIST;
			req.setAttribute("allEmployees", employeeDAO.getEmployees());
		}
		 else if(action.equalsIgnoreCase("update")){
			 
			 forward=UPDATE;
		     int id = Integer.parseInt(req.getParameter("employeeId"));
			 Employee emp =new Employee();
			
			 emp=employeeDAO.getEmployee(id);
			 req.setAttribute("employee", emp);
		 }	
		 else if(action.equalsIgnoreCase("listEmployee")){
		forward=LIST;
		 
		List<Employee> employeeList = employeeDAO.getEmployees();
		
		req.setAttribute("allEmployees", employeeList);
		 }
		 
		RequestDispatcher dispatcher = req.getRequestDispatcher(forward);
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String surname= req.getParameter("surname");
		int salary = Integer.parseInt(req.getParameter("salary"));
		//
		
		String StrId = req.getParameter("id");
		
		if(StrId==null){
		employeeDAO.insertEmployee(name, surname, salary);
		}
		else{
			int id=Integer.parseInt(StrId);
		    employeeDAO.updateEmployee(id, name, surname, salary);
		}
		//
		List<Employee> employeeList = employeeDAO.getEmployees();
		//
		req.setAttribute("allEmployees", employeeList);
		//
		RequestDispatcher dispatcher = req.getRequestDispatcher(LIST);
		dispatcher.forward(req, resp);
	}	
}
