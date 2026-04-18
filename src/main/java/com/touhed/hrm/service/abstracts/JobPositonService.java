package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobPositionDto;
import com.touhed.hrm.dto.request.CreateJobPositionRequest;

import java.util.List;

public interface JobPositonService {
	
	Result add( CreateJobPositionRequest request );
	DataResult<List<JobPositionDto>> getAll();
}
