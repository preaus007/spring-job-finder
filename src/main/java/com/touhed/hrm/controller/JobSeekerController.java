package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobSeekerDto;
import com.touhed.hrm.dto.request.JobSeekerRegisterRequest;
import com.touhed.hrm.service.abstracts.JobSeekerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidateController")
public class JobSeekerController {

	private final JobSeekerService jobSeekerService;

	public JobSeekerController( JobSeekerService jobSeekerService ) {
		this.jobSeekerService = jobSeekerService;
	}

	@PostMapping("/register")
	public Result register( @Valid @RequestBody JobSeekerRegisterRequest request ) {
		return jobSeekerService.register( request );
	}

	@GetMapping("/getAll")
	public DataResult<List<JobSeekerDto>> getAll() {
		return jobSeekerService.getAll();
	}

}
