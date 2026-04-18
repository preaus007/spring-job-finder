package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.EmployerDto;
import com.touhed.hrm.dto.request.EmployerRegisterRequest;
import com.touhed.hrm.service.abstracts.EmployerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

	private final EmployerService employerService;

	public EmployersController( EmployerService employerService ) {
		this.employerService = employerService;
	}

	@PostMapping("/register")
	public Result register( @Valid @RequestBody EmployerRegisterRequest request ) {
		return employerService.register( request );
	}

	@GetMapping("/getAll")
	public DataResult<List<EmployerDto>> getAll() {
		return employerService.getAll();
	}

}
