package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.utilities.*;
import com.touhed.hrm.dto.EmployerDto;
import com.touhed.hrm.dto.request.EmployerRegisterRequest;
import com.touhed.hrm.entity.Employer;
import com.touhed.hrm.repository.EmployerDao;
import com.touhed.hrm.service.abstracts.EmployerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployerManager implements EmployerService {

	private final EmployerDao employerDao;
	private final PasswordEncoder passwordEncoder;

	public EmployerManager( EmployerDao employerDao, PasswordEncoder passwordEncoder ) {
		this.employerDao = employerDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Result register( EmployerRegisterRequest request ) {

		if( !request.getPassword().equals( request.getConfirmPassword() ) )
			return new ErrorResult( "Passwords do not match." );

        if( employerDao.findByEmail( request.getEmail() ).isPresent() )
			return new ErrorResult( "Email is already in use." );

		Employer employer = new Employer();
		employer.setCompanyName( request.getCompanyName() );
		employer.setCompanyWebPage( request.getCompanyWebPage() );
		employer.setEmail( request.getEmail() );
		employer.setPhoneNumber( request.getPhoneNumber() );
		employer.setPassword( passwordEncoder.encode( request.getPassword() ) );
		employerDao.save( employer );

		return new SuccessResult( "Employer registered." );
	}

	@Override
	public DataResult< List<EmployerDto> > getAll() {

        List<EmployerDto> employerList = employerDao.findAll().stream()
                .map(e ->
                        new EmployerDto( e.getId(), e.getCompanyName(), e.getCompanyWebPage(), e.getEmail(), e.getPhoneNumber() )
                ).toList();
        return new SuccessDataResult<>( employerList, "Employers listed." );
	}
}
