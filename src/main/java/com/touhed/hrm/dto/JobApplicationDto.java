package com.touhed.hrm.dto;

import java.time.LocalDateTime;

import com.touhed.hrm.core.responses.EnumResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

	private Long id;
	private Long jobAdvertisementId;
	private Long jobSeekerId;
	private String jobTitle;
	private String employerCompanyName;
	private String applicationDate;
	private EnumResponse status;
}
