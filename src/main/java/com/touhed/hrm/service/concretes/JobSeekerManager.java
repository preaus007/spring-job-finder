package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.ErrorResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessDataResult;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.dto.JobSeekerDto;
import com.touhed.hrm.dto.request.JobSeekerRegisterRequest;
import com.touhed.hrm.entity.JobSeeker;
import com.touhed.hrm.repository.JobSeekerDao;
import com.touhed.hrm.service.abstracts.JobSeekerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerManager implements JobSeekerService {

	private final JobSeekerDao jobSeekerDao;
	private final PasswordEncoder passwordEncoder;

	public JobSeekerManager( JobSeekerDao jobSeekerDao, PasswordEncoder passwordEncoder ) {
		this.jobSeekerDao = jobSeekerDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Result register( JobSeekerRegisterRequest request ) {
		if( !request.getPassword().equals( request.getConfirmPassword() ) ) {
			return new ErrorResult( "Passwords do not match." );
		}
		if( jobSeekerDao.findByEmail( request.getEmail() ).isPresent() ) {
			return new ErrorResult( "Email is already in use." );
		}
		if( jobSeekerDao.findByNationalId( request.getNationalId() ).isPresent() ) {
			return new ErrorResult( "National ID is already in use." );
		}

		JobSeeker js = new JobSeeker();
		js.setFirstName( request.getFirstName() );
		js.setLastName( request.getLastName() );
		js.setNationalId( request.getNationalId() );
		js.setBirthDate( request.getBirthDate() );
		js.setEmail( request.getEmail() );
		js.setPassword( passwordEncoder.encode( request.getPassword() ) );
		jobSeekerDao.save( js );

		return new SuccessResult( "Job seeker registered." );
	}

	@Override
	public DataResult<List<JobSeekerDto>> getAll() {
		var list = jobSeekerDao.findAll().stream().map( j -> new JobSeekerDto(
				j.getId(),
				j.getFirstName(),
				j.getLastName(),
				j.getNationalId(),
				j.getBirthDate() == null ? null : j.getBirthDate().toString(),
				j.getEmail()
		) ).toList();
		return new SuccessDataResult<>( list, "Job seekers listed." );
	}

}
