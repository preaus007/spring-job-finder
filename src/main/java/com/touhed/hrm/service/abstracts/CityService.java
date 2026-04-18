package com.touhed.hrm.service.abstracts;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.CityDto;
import com.touhed.hrm.dto.request.CreateCityRequest;

import java.util.List;

public interface CityService {

	Result add( CreateCityRequest request );
	DataResult< List<CityDto> > getAll();
}
