package com.touhed.hrm.service.concretes;

import java.util.List;

import com.touhed.hrm.core.utilities.*;
import com.touhed.hrm.dto.CityDto;
import com.touhed.hrm.dto.request.CreateCityRequest;
import com.touhed.hrm.entity.City;
import com.touhed.hrm.repository.CityDao;
import com.touhed.hrm.service.abstracts.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityManager implements CityService {

	private final CityDao cityDao;

	public CityManager( CityDao cityDao ) {
		this.cityDao = cityDao;
	}

	@Override
	public Result add( CreateCityRequest request ) {

        if( cityDao.findByCityName( request.getCityName() ).isPresent() )
            return new ErrorResult( "City already exists." );

        City city = new City();
		city.setCityName( request.getCityName() );
		cityDao.save( city );
		return new SuccessResult( "City created." );
	}

	@Override
	public DataResult< List<CityDto> > getAll() {

        List<CityDto> cityList = cityDao.findAll().stream()
                .map( c -> new CityDto( c.getId(), c.getCityName() ) )
                .toList();

        return new SuccessDataResult<>( cityList, "Cities listed." );
	}

}
