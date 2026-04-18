package com.touhed.hrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementDto {

	private Long id;
	private String jobTitle;
	private String companyName;
	private String city;
	private Integer openPositionCount;
	private Double minSalary;
	private Double maxSalary;
	private String releaseDate;
	private String applicationDeadline;
	private boolean active;
}
