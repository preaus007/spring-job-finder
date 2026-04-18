package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.responses.EnumResponse;
import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.ErrorResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessDataResult;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.dto.JobApplicationDto;
import com.touhed.hrm.dto.request.ApplyJobRequest;
import com.touhed.hrm.dto.request.UpdateApplicationStatusRequest;
import com.touhed.hrm.entity.JobAdvertisement;
import com.touhed.hrm.entity.JobApplication;
import com.touhed.hrm.entity.JobSeeker;
import com.touhed.hrm.enums.JobApplicationStatus;
import com.touhed.hrm.repository.JobAdvertisementDao;
import com.touhed.hrm.repository.JobApplicationDao;
import com.touhed.hrm.repository.JobSeekerDao;
import com.touhed.hrm.service.abstracts.JobApplicationService;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationManager implements JobApplicationService {

	private final JobApplicationDao jobApplicationDao;
	private final JobAdvertisementDao jobAdvertisementDao;
	private final JobSeekerDao jobSeekerDao;

	public JobApplicationManager( JobApplicationDao jobApplicationDao, JobAdvertisementDao jobAdvertisementDao,
			JobSeekerDao jobSeekerDao ) {
		this.jobApplicationDao = jobApplicationDao;
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public Result apply( ApplyJobRequest request ) {
		JobAdvertisement ad = jobAdvertisementDao.findById( request.getJobAdvertisementId() )
				.orElseThrow( () -> new IllegalArgumentException( "Job advertisement not found." ) );
		if( !ad.isActive() ) {
			return new ErrorResult( "Job advertisement is not active." );
		}

		JobSeeker seeker = jobSeekerDao.findById( request.getJobSeekerId() )
				.orElseThrow( () -> new IllegalArgumentException( "Job seeker not found." ) );

		if( jobApplicationDao.findByJobAdvertisement_IdAndJobSeeker_Id( ad.getId(), seeker.getId() ).isPresent() ) {
			return new ErrorResult( "You have already applied to this advertisement." );
		}

		JobApplication app = new JobApplication();
		app.setJobAdvertisement( ad );
		app.setJobSeeker( seeker );
		app.setStatus( JobApplicationStatus.PENDING );

		jobApplicationDao.save( app );
		return new SuccessResult( "Application submitted." );
	}

	@Override
	public Result updateStatus( UpdateApplicationStatusRequest request ) {
		JobApplication app = jobApplicationDao.findById( request.getApplicationId() )
				.orElseThrow( () -> new IllegalArgumentException( "Application not found." ) );
		app.setStatus( request.getStatus() );
		jobApplicationDao.save( app );
		return new SuccessResult( "Application status updated." );
	}

	@Override
	public DataResult<List<JobApplicationDto>> getByAdvertisement( Long jobAdvertisementId ) {
		var list = jobApplicationDao.findByJobAdvertisement_Id( jobAdvertisementId ).stream()
				.map( this::toDto )
				.toList();
		return new SuccessDataResult<>( list, "Applications listed for advertisement." );
	}

	@Override
	public DataResult<List<JobApplicationDto>> getByJobSeeker( Long jobSeekerId ) {
		var list = jobApplicationDao.findByJobSeeker_Id( jobSeekerId ).stream()
				.map( this::toDto )
				.toList();
		return new SuccessDataResult<>( list, "Applications listed for job seeker." );
	}

	private JobApplicationDto toDto( JobApplication application ) {
		return new JobApplicationDto(
				application.getId(),
				application.getJobAdvertisement().getId(),
				application.getJobSeeker().getId(),
				application.getJobAdvertisement().getJobPosition().getTitle(),
				application.getJobAdvertisement().getEmployer().getCompanyName(),
				application.getApplicationDate() == null ? null : application.getApplicationDate().toString(),
				toEnumResponse( application.getStatus() )
		);
	}

	private EnumResponse toEnumResponse( JobApplicationStatus status ) {
		if( status == null ) {
			return null;
		}
		return new EnumResponse( status.getValue(), status.name() );
	}
}