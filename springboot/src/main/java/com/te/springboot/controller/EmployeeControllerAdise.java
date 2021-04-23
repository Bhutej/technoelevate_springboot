package com.te.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.springboot.bean.EmployeeResponse;
import com.te.springboot.customexception.EmployeeExp;

@RestControllerAdvice
public class EmployeeControllerAdise {
	
	@ExceptionHandler(EmployeeExp.class)
	public EmployeeResponse empExp(EmployeeExp employeeExp) {
		
		EmployeeResponse response = new EmployeeResponse();
		response.setStatusCode(500);
		response.setMsg(employeeExp.getMessage());
		return response;
		
	}

}
