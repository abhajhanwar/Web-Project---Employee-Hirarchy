package RestServicesClasses;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.json.simple.JSONObject;

@Path("/employees")
public class EmployeeResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "JSON" media type.
     *
     * @return JSON object that will be returned as a JSON response.
     */
	EmployeeService employeeService= new EmployeeService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getEmployeeHierarchy() throws IOException {

    	return employeeService.getEmployeeHierarchy();
        }
    }


