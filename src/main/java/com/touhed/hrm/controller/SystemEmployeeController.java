package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.entity.SystemEmployee;
import com.touhed.hrm.service.abstracts.SystemEmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hrController")
public class SystemEmployeeController {

	private final SystemEmployeeService systemEmployeeService;

	public SystemEmployeeController( SystemEmployeeService systemEmployeeService ) {
		this.systemEmployeeService = systemEmployeeService;
	}

	@PostMapping("/add")
	public Result add( @RequestBody SystemEmployee systemEmployee ) {
		return this.systemEmployeeService.add( systemEmployee );
	}

	@GetMapping("/getAll/systemEmployee")
	public DataResult<List<SystemEmployee>> getAll() {
		return this.systemEmployeeService.getAll();
	}

}
