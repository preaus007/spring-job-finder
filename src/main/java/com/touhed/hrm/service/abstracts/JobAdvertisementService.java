package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.JobAdvertisementDto;
import com.touhed.hrm.dto.request.CreateJobAdvertisementRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface JobAdvertisementService {

	Result add( CreateJobAdvertisementRequest request );

	DataResult< List<JobAdvertisementDto> > getAll();

	DataResult< List<JobAdvertisementDto> > getActive();

	DataResult< List<JobAdvertisementDto> > getActiveByEmployer( Integer employerId );

	DataResult< List<JobAdvertisementDto> > getSortedByDeadlineAsc();

	DataResult< List<JobAdvertisementDto> > getByDeadline( LocalDateTime date );
}
