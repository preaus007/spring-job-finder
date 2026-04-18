package com.touhed.hrm.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobAdvertisementDto;
import com.touhed.hrm.dto.request.CreateJobAdvertisementRequest;
import com.touhed.hrm.service.abstracts.JobAdvertisementService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobPost")
public class JobAdvertisementController {

	private final JobAdvertisementService jobAdvertisementService;

	public JobAdvertisementController( JobAdvertisementService jobAdvertisementService ) {
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@PostMapping("/add")
	public Result add( @Valid @RequestBody CreateJobAdvertisementRequest request ) {
		return jobAdvertisementService.add( request );
	}

	@GetMapping("/getAll")
	public DataResult<List<JobAdvertisementDto>> getAll() {
		return jobAdvertisementService.getAll();
	}

	@GetMapping("/active")
	public DataResult<List<JobAdvertisementDto>> getActive() {
		return jobAdvertisementService.getActive();
	}

	@GetMapping("/active/by-employer")
	public DataResult<List<JobAdvertisementDto>> getActiveByEmployer( @RequestParam Integer employerId ) {
		return jobAdvertisementService.getActiveByEmployer( employerId );
	}

	@GetMapping("/sorted-by-deadline")
	public DataResult<List<JobAdvertisementDto>> getSortedByDeadlineAsc() {
		return jobAdvertisementService.getSortedByDeadlineAsc();
	}

	@GetMapping("/by-deadline")
	public DataResult<List<JobAdvertisementDto>> getByDeadline(
			@RequestParam @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime date ) {
		return jobAdvertisementService.getByDeadline( date );
	}
}