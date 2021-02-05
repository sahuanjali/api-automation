package com.efx.assignment.auto.test.api.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.efx.assignment.auto.test.api.Employee;
import com.efx.assignment.auto.test.api.EmployeeData;
import com.efx.assignment.auto.test.api.GetRestData;

import io.restassured.response.Response;

public class RestAssuredTest {
	
	GetRestData restData;
	
	/**
	 * Initialize GetRestData Object
	 */
	@BeforeTest
	public void setUp(){
		
		restData = new GetRestData();
	}
	
	/**
	 * Perform Assretion for Successful status code for get.
	 */
	@Test
	public void test_get_status_code() {

		Response response = restData.getRestData(1);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	/**
	 * Preform Assertion for Successful status code for delete. 
	 */
	@Test
	public void test_delete_status_code() {

		Response response = restData.deleteRestData(1);	
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	/**
	 * Perform Assertion for a specific employee detiail 
	 */
	@Test
	public void test_get_employee_details() {

		Response response = restData.getRestData(1);
		EmployeeData responActualSeEmpDetails = response.as(EmployeeData.class);
		
		System.out.println(" Response "+responActualSeEmpDetails.getData().getEmployee_name());
		
		EmployeeData expectedEmployeeJson = new EmployeeData();
		expectedEmployeeJson.setStatus("success");
		Employee data = new Employee();
			data.setId(1);
			data.setEmployee_name("Tiger Nixon");
			data.setEmployee_salary(320800);
			data.setEmployee_age(61);
			data.setProfile_image("");
			
		expectedEmployeeJson.setData(data);
		expectedEmployeeJson.setMessage("Successfully! Record has been fetched.");
	

		Assert.assertEquals(responActualSeEmpDetails.getData().getId(), expectedEmployeeJson.getData().getId());
		Assert.assertEquals(responActualSeEmpDetails.getData().getEmployee_age(), expectedEmployeeJson.getData().getEmployee_age());
		Assert.assertEquals(responActualSeEmpDetails.getData().getEmployee_age(), expectedEmployeeJson.getData().getEmployee_age());
		Assert.assertEquals(responActualSeEmpDetails.getData().getEmployee_name(), expectedEmployeeJson.getData().getEmployee_name());
		Assert.assertEquals(responActualSeEmpDetails.getData().getEmployee_salary(), expectedEmployeeJson.getData().getEmployee_salary());
		Assert.assertEquals(responActualSeEmpDetails.getData().getProfile_image(), expectedEmployeeJson.getData().getProfile_image());

	}

	/**
	 * Perform Assertion for a delete employee message 
	 */
	@Test
	public void test_delete_employee_data() {

		JSONObject request = new JSONObject();
		
		given().
		body(request.toJSONString()).
		when().
		delete("http://dummy.restapiexample.com/public/api/v1/delete/2").
		then().statusCode(200).
		body("message", equalTo("Successfully! Record has been deleted"))
		.log().all();

	}
}