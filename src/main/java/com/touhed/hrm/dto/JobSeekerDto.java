package com.touhed.hrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerDto {

	private Long id;
	private String name;
	private String lastName;
	private String nationalId;
	private String birthDate;
	private String email;
}
