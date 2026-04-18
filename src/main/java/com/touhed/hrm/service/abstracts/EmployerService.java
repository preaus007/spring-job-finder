package com.touhed.hrm.service.abstracts;


import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.EmployerDto;
import com.touhed.hrm.dto.request.EmployerRegisterRequest;
import java.util.List;

public interface EmployerService {

	Result register( EmployerRegisterRequest request );

	DataResult< List<EmployerDto> > getAll();
}
