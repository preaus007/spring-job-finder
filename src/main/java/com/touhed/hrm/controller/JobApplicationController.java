package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobApplicationDto;
import com.touhed.hrm.dto.request.ApplyJobRequest;
import com.touhed.hrm.dto.request.UpdateApplicationStatusRequest;
import com.touhed.hrm.service.abstracts.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

	private final JobApplicationService jobApplicationService;

	public JobApplicationController( JobApplicationService jobApplicationService ) {
		this.jobApplicationService = jobApplicationService;
	}

	@PostMapping("/apply")
	public Result apply( @Valid @RequestBody ApplyJobRequest request ) {
		return jobApplicationService.apply( request );
	}

	@PostMapping("/update-status")
	public Result updateStatus( @Valid @RequestBody UpdateApplicationStatusRequest request ) {
		return jobApplicationService.updateStatus( request );
	}

	@GetMapping("/by-advertisement/{adId}")
	public DataResult<List<JobApplicationDto>> getByAdvertisement( @PathVariable("adId") Long adId ) {
		return jobApplicationService.getByAdvertisement( adId );
	}

	@GetMapping("/by-jobseeker/{seekerId}")
	public DataResult<List<JobApplicationDto>> getByJobSeeker( @PathVariable("seekerId") Long seekerId ) {
		return jobApplicationService.getByJobSeeker( seekerId );
	}
}
