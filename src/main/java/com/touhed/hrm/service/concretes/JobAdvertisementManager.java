package com.touhed.hrm.service.concretes;

import java.time.LocalDateTime;
import java.util.List;

import com.touhed.hrm.core.exceptions.NotFoundException;
import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.ErrorResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessDataResult;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.dto.JobAdvertisementDto;
import com.touhed.hrm.dto.request.CreateJobAdvertisementRequest;
import com.touhed.hrm.entity.City;
import com.touhed.hrm.entity.Employer;
import com.touhed.hrm.entity.JobAdvertisement;
import com.touhed.hrm.entity.JobPosition;
import com.touhed.hrm.repository.CityDao;
import com.touhed.hrm.repository.EmployerDao;
import com.touhed.hrm.repository.JobAdvertisementDao;
import com.touhed.hrm.repository.JobPositonDao;
import com.touhed.hrm.service.abstracts.JobAdvertisementService;
import org.springframework.stereotype.Service;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private final JobAdvertisementDao jobAdvertisementDao;
	private final JobPositonDao jobPositionDao;
	private final CityDao cityDao;
	private final EmployerDao employerDao;

	public JobAdvertisementManager( JobAdvertisementDao jobAdvertisementDao, JobPositonDao jobPositionDao,
			CityDao cityDao, EmployerDao employerDao ) {
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.jobPositionDao = jobPositionDao;
		this.cityDao = cityDao;
		this.employerDao = employerDao;
	}

	@Override
	public Result add( CreateJobAdvertisementRequest request ) {
		if( request.getMinSalary() != null
				&& request.getMaxSalary() != null
				&& request.getMinSalary() > request.getMaxSalary() ) {
			return new ErrorResult( "Min salary cannot be greater than max salary." );
		}

		JobPosition position = jobPositionDao.findById( request.getJobPositionId() )
				.orElseThrow( () -> new NotFoundException( "jobPosition", request.getJobPositionId() ) );

		City city = cityDao.findById( request.getCityId() )
				.orElseThrow( () -> new NotFoundException( "city", request.getCityId() ) );

		Employer employer = employerDao.findById( request.getEmployerId() )
				.orElseThrow( () -> new NotFoundException( "employer", request.getEmployerId() ) );

		JobAdvertisement ad = new JobAdvertisement();
		ad.setJobPosition( position );
		ad.setCity( city );
		ad.setEmployer( employer );
		ad.setDescription( request.getDescription() );
		ad.setOpenPositionCount( request.getOpenPositionCount() );
		ad.setMinSalary( request.getMinSalary() );
		ad.setMaxSalary( request.getMaxSalary() );
		ad.setReleaseDate( LocalDateTime.now() );
		ad.setApplicationDeadline( request.getApplicationDeadline() );
		ad.setActive( true );

		jobAdvertisementDao.save( ad );
		return new SuccessResult( "Job advertisement created." );
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getAll() {
		var list = jobAdvertisementDao.findAll().stream().map( this::toDto ).toList();
		return new SuccessDataResult<>( list, "Job advertisements listed." );
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActive() {
		var list = jobAdvertisementDao.findByActiveTrue().stream().map( this::toDto ).toList();
		return new SuccessDataResult<>( list, "Active job advertisements listed." );
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveByEmployer( Integer employerId ) {
		var list = jobAdvertisementDao.findByEmployerIdAndActiveTrue( employerId.longValue() ).stream().map( this::toDto )
				.toList();
		return new SuccessDataResult<>( list, "Employer's active job advertisements listed." );
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getSortedByDeadlineAsc() {
		var list = jobAdvertisementDao.findAllByOrderByApplicationDeadlineAsc().stream().map( this::toDto ).toList();
		return new SuccessDataResult<>( list, "Job advertisements sorted by deadline." );
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getByDeadline( LocalDateTime date ) {
		var list = jobAdvertisementDao.findByApplicationDeadline( date ).stream().map( this::toDto ).toList();
		return new SuccessDataResult<>( list, "Job advertisements for given deadline." );
	}

	private JobAdvertisementDto toDto( JobAdvertisement ad ) {
		return new JobAdvertisementDto(
				ad.getId(),
				ad.getJobPosition().getTitle(),
				ad.getEmployer().getCompanyName(),
				ad.getCity().getCityName(),
				ad.getOpenPositionCount(),
				ad.getMinSalary(),
				ad.getMaxSalary(),
				ad.getReleaseDate() == null ? null : ad.getReleaseDate().toString(),
				ad.getApplicationDeadline() == null ? null : ad.getApplicationDeadline().toString(),
				ad.isActive()
		);
	}
}
