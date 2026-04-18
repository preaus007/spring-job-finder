package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobApplicationDto;
import com.touhed.hrm.dto.request.ApplyJobRequest;
import com.touhed.hrm.dto.request.UpdateApplicationStatusRequest;

import java.util.List;

public interface JobApplicationService {

	Result apply( ApplyJobRequest request );

	Result updateStatus( UpdateApplicationStatusRequest request );

	DataResult<List<JobApplicationDto>> getByAdvertisement( Long jobAdvertisementId );

	DataResult<List<JobApplicationDto>> getByJobSeeker( Long jobSeekerId );
}
