package com.te.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.springboot.bean.EmployeeBean;
import com.te.springboot.bean.EmployeeResponse;
import com.te.springboot.service.EmployeeService;

@RestController
public class EmplBootController {

	@Autowired
	private EmployeeService service;

	@GetMapping(path = "/getEmp", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getEmployee(int id) {
		EmployeeResponse response = new EmployeeResponse();
		EmployeeBean bean = service.getEmployee(id);
		if (bean != null) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Employee data found for id : " + id);
			response.setBean(bean);
		} else {

			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Employee data not found for id : " + id);

		}

		return response;

	}

	@GetMapping(path = "/allEmp", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getAllEmp() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBean> beans = service.getAllEmp();
		if (beans != null) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Employee details found");
			response.setEmployeeBeans(beans);
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Details Not found");
		}
		return response;

	}

	@DeleteMapping(path = "/delete/{emp_id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse getDelEmp(@PathVariable(name = "emp_id") int id) {
		EmployeeResponse response = new EmployeeResponse();
		if (service.deleteEmpData(id)) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Employee details are deleted");
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription(" Data Not for id : " + id);

		}
		return response;

	}

	@PostMapping(path = "/addEmp", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse registerEmp(@RequestBody EmployeeBean employeeBean) {
		EmployeeResponse response = new EmployeeResponse();
		if (service.addEmployee(employeeBean)) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Added Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Something Went Wrong");
		}
		return response;
	}
	
	@PutMapping(path = "/update", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeResponse updateEmp(@RequestBody EmployeeBean bean) {
		System.out.println(bean);
		EmployeeResponse response = new EmployeeResponse();
		if (service.updateEmployee(bean)) {
			response.setStatusCode(200);
			response.setMsg("success");
			response.setDescription("Updated Successfully");
		} else {
			response.setStatusCode(400);
			response.setMsg("failure");
			response.setDescription("Something Went Wrong");
		}
		return response;
	}

}
