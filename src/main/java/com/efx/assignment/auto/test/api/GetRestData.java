package com.efx.assignment.auto.test.api;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
*This class help to get employee and delete employee 
*
*/
public class GetRestData {
	
	/**
	 * Get employee data from rest end point API 
	 * @param employeeId
	 * @return
	 */
	public Response getRestData(int employeeId) {
		
		Response response = RestAssured.get("http://dummy.restapiexample.com/api/v1/employee/"+employeeId);
		return response;
	}
	
	/**
	 * delete employee data from rest end point API 
	 * @param employeeId
	 * @return
	 */
	public Response deleteRestData(int employeeId) {
		
		Response response = RestAssured.delete("http://dummy.restapiexample.com/public/api/v1/delete/"+employeeId);
		return response;
	}

}
