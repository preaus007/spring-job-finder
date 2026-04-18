package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobPositionDto;
import com.touhed.hrm.dto.request.CreateJobPositionRequest;
import com.touhed.hrm.service.abstracts.JobPositonService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobPosition")
public class JobPositionController {

	private final JobPositonService jobPositonService;

	public JobPositionController( JobPositonService jobPositonService ) {
		this.jobPositonService = jobPositonService;
	}

	@PostMapping("/add")
	public Result add( @Valid @RequestBody CreateJobPositionRequest request ) {
		return jobPositonService.add( request );
	}

	@GetMapping("/getAll")
	public DataResult<List<JobPositionDto>> getAll() {
		return jobPositonService.getAll();
	}

}
