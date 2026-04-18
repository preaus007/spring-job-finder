package com.touhed.hrm.controller;

import java.util.List;

import com.touhed.hrm.core.utilities.DataResult;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.dto.CityDto;
import com.touhed.hrm.dto.request.CreateCityRequest;
import com.touhed.hrm.service.abstracts.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	private final CityService cityService;

	public CityController( CityService cityService ) {
		this.cityService = cityService;
	}

	@PostMapping("/add")
	public Result add( @Valid @RequestBody CreateCityRequest request ) {
		return cityService.add( request );
	}

	@GetMapping("/getAll")
	public DataResult<List<CityDto>> getAll() {
		return cityService.getAll();
	}

}
