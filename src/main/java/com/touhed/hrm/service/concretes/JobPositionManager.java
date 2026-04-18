package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.ErrorResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessDataResult;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.dto.JobPositionDto;
import com.touhed.hrm.dto.request.CreateJobPositionRequest;
import com.touhed.hrm.entity.JobPosition;
import com.touhed.hrm.repository.JobPositonDao;
import com.touhed.hrm.service.abstracts.JobPositonService;
import org.springframework.stereotype.Service;

@Service
public class JobPositionManager implements JobPositonService {

	private final JobPositonDao jobPositonDao;

	public JobPositionManager( JobPositonDao jobPositonDao ) {
		this.jobPositonDao = jobPositonDao;
	}

	@Override
	public Result add( CreateJobPositionRequest request ) {
		if( jobPositonDao.findByTitle( request.getTitle() ).isPresent() ) {
			return new ErrorResult( "Job position already exists." );
		}
		JobPosition p = new JobPosition();
		p.setTitle( request.getTitle() );
		jobPositonDao.save( p );
		return new SuccessResult( "Job position created." );
	}

	@Override
	public DataResult<List<JobPositionDto>> getAll() {
		var list = jobPositonDao.findAll().stream().map( j -> new JobPositionDto( j.getId(), j.getTitle() ) ).toList();
		return new SuccessDataResult<>( list, "Job positions listed." );
	}
}
