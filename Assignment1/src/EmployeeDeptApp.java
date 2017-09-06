import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class EmployeeDeptApp {

	public static void main(String[] args) throws SQLException 
	{
		DataDAO condb = new  DataDAO();
		
		List<Employee> emp = condb.getAllEmployeeData();
		Iterator<Employee> empitr = emp.iterator();
		System.out.println("Employee Data:");
		System.out.println("EmpId EmpName   EmpAddress  EmpSalary EmpDesgn EmpDeptId");
		while(empitr.hasNext())
		{
			Employee empobj = empitr.next();
			String empData = empobj.getEmpId() + "   " + empobj.getEmpName() + "      " + empobj.getEmpAddress() + "   " + empobj.getEmpSalary() + " " + empobj.getEmpDesignation() + "      " + empobj.getDepId();
			System.out.println(empData);
		}
		
		List<Department> dep = condb.getAllDepartmentData();
		Iterator<Department> depitr = dep.iterator();
		System.out.println("Department Data:");
		System.out.println("DeptId DeptName HodId");		
		while(depitr.hasNext())
		{
			Department depobj = depitr.next();
			String depData = depobj.getDepID() + "      " + depobj.getDepName() + "    " + depobj.getHodId();
			System.out.println(depData);
		}
		
		Employee empupd = condb.updateEmpoyeeAddress("abc", "A Street, City");
		System.out.println(empupd.getEmpAddress());
		
		String deptName = "Testing";
		List<Employee> empdet = condb.getDetailsforDepartment(deptName);
		System.out.println("Employees for Department " + deptName);
		for(Employee temp : empdet)
		{
			String empData = temp.getEmpId() + "      " + temp.getEmpName() + "      " + temp.getEmpAddress();
			System.out.println(empData);
		}
	}

}
