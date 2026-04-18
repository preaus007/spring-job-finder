package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobSeekerDto;
import com.touhed.hrm.dto.request.JobSeekerRegisterRequest;

import java.util.List;

public interface JobSeekerService {
	
	Result register( JobSeekerRegisterRequest request );
	DataResult<List<JobSeekerDto>> getAll();
}
