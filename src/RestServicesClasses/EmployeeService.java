package RestServicesClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RestServicesClasses.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmployeeService {
	
	Map<Integer, Employee> employees;
    Employee topEmployee;
	JSONObject EmployeeHierarchy = new JSONObject();

	public JSONObject getEmployeeHierarchy() throws IOException{
        employees = new HashMap<>();
        readDataAndCreateEmployees();
        validateEmployeesData();
        EmployeeHierarchy = buildHierarchy(topEmployee);
        return EmployeeHierarchy;
       
	}
	private void readDataAndCreateEmployees() throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.FileReader("C:/Users/abhaskar/workspace/EmployeeHierarchy/src/Files/employees.txt"));
        String line = reader.readLine();
        while (line != null) {
            Employee employee = createEmployee(line);
            employees.put(employee.getId(), employee);
            if (employee.getMgrId() == 0) {
                topEmployee = employee;
            }
            line = reader.readLine();
        }
    }
	private Employee createEmployee(String line) {
        String[] values = line.split(" ");
        Employee employee = null;
        try {
            if (values.length ==2) {
                employee = new Employee(values[0], values[1]);
            }else if(values.length==3){
                employee = new Employee(values[0], values[1], values[2]);
            }
        } catch (Exception e) {
            System.out.println("Unable to create Employee as the data is " + values);
        }
        return employee;
    }
	private JSONObject buildHierarchy(Employee topEmployee) {
        
		JSONObject obj = new JSONObject();
        obj.put("name", topEmployee.getEmpName());
        obj.put("subordinates", new JSONArray());
        
        List<Employee> employees1 = findAllEmployeesHavingMgrId(topEmployee.getId());

        for (Employee e : employees1) {
            JSONObject empHierarchy = buildHierarchy(e);
            JSONArray subordinates = (JSONArray) obj.get("subordinates");
            subordinates.add(empHierarchy);
            obj.put("subordinates", subordinates);
        }
        return obj;
    }
	
	 public List<Employee> findAllEmployeesHavingMgrId(int mgrid) {
	        List<Employee> sameMgrEmployees = new ArrayList<>();
	        for (Employee e : employees.values()) {
	            if (e.getMgrId() == mgrid) {
	                sameMgrEmployees.add(e);
	            }
	        }
	        return sameMgrEmployees;
	    }
	 private void validateEmployeesData() {    	

	        List<Integer> invalidEmployees = new ArrayList<>();
	        for(Employee e: employees.values()){
	            if(e.getId() == e.getMgrId()){
	                System.out.println("Employee "+e.getEmpName()+" is its own manager. It is not possible");
	                invalidEmployees.add(e.getId());
	            }
	            else if(e.getMgrId()!=0 && employees.get(e.getMgrId())==null){
	                System.out.println("Employee "+e.getEmpName()+"'s manager with manager id "+e.getMgrId()+" does not exists");
	                invalidEmployees.add(e.getId());
	            }
	        }

	        for(Integer invalid: invalidEmployees){
	            employees.remove(invalid);
	        }
	    }
	
		
	}

