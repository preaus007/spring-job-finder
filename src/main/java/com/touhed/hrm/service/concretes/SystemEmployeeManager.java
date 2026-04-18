package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessDataResult;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.entity.SystemEmployee;
import com.touhed.hrm.repository.SystemEmployeeDao;
import com.touhed.hrm.service.abstracts.SystemEmployeeService;
import org.springframework.stereotype.Service;

@Service
public class SystemEmployeeManager implements SystemEmployeeService {

	private final SystemEmployeeDao systemEmployeeDao;

	public SystemEmployeeManager( SystemEmployeeDao systemEmployeeDao ) {
		this.systemEmployeeDao = systemEmployeeDao;
	}

	@Override
	public DataResult<List<SystemEmployee>> getAll() {
		return new SuccessDataResult<>( this.systemEmployeeDao.findAll(), "System employees listed." );
	}

	@Override
	public Result add( SystemEmployee systemEmployee ) {
		this.systemEmployeeDao.save( systemEmployee );
		return new SuccessResult( "System employee added." );
	}
}
