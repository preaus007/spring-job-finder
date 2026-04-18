package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.entity.SystemEmployee;

import java.util.List;

public interface SystemEmployeeService {
	
	DataResult<List<SystemEmployee>> getAll();
	Result add(SystemEmployee systemEmployee);
}
