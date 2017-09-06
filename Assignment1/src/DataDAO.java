import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDAO 
{	
	Employee emp = null;
	Department dep = null;
			
	public List<Employee> getAllEmployeeData() throws SQLException
	{
	    String query = "SELECT * FROM employee;";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelgeek","admin","admin123");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		List<Employee> employeelist = new ArrayList<Employee>();
		
		while(rs.next())
		{
			emp = new Employee();
			emp.setEmpId(rs.getInt(1));
			emp.setEmpName(rs.getString(2));
			emp.setEmpAddress(rs.getString(3));
			emp.setEmpSalary(rs.getDouble(4));
			emp.setEmpDesignation(rs.getString(5));
			emp.setDepId(rs.getInt(6));
			
			employeelist.add(emp);
		}
		
		return employeelist;
	}
	
	public List<Department> getAllDepartmentData() throws SQLException
	{
	    String query = "SELECT * FROM department;";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelgeek","admin","admin123");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		List<Department> deplist = new ArrayList<Department>();
		
		while(rs.next())
		{
			dep = new Department();
			dep.setDepID(rs.getInt(1));
			dep.setDepName(rs.getString(2));
			dep.setHodId(rs.getInt(3));

			deplist.add(dep);
		}
		
		return deplist;
	}
	
	public Employee updateEmpoyeeAddress(String empName,String empAddress)
	{
	    String query1 = String.format("UPDATE employee set empaddress = '%s' where empname = '%s';", empAddress,empName);
	    String query2= String.format("SELECT * from employee where empname = '%s';",empName);
		Connection con;
		try 
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelgeek","admin","admin123");
			Statement stmt = con.createStatement();
			stmt.execute(query1);
			
			stmt.close();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query2);	
			while(rs.next())
			{
			emp = new Employee();
			emp.setEmpId(rs.getInt(1));
			emp.setEmpName(rs.getString(2));
			emp.setEmpAddress(rs.getString(3));
			emp.setEmpSalary(rs.getDouble(4));
			emp.setEmpDesignation(rs.getString(5));
			emp.setDepId(rs.getInt(6));
			}
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}
	
	public List<Employee> getDetailsforDepartment(String depName)
	{
	    String query = String.format("SELECT employee.* FROM employee INNER JOIN department on employee.deptid = department.deptid WHERE department.deptname = '%s';", depName);
		Connection con;
		List<Employee> emplist = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelgeek","admin","admin123");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			emplist = new ArrayList<Employee>();
			while(rs.next())
			{
				emp = new Employee();
				emp.setEmpId(rs.getInt("empid"));
				emp.setEmpName(rs.getString("empname"));				
				emp.setEmpAddress(rs.getNString("empaddress"));
				emplist.add(emp);
			}
				
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return emplist;
	}
}
