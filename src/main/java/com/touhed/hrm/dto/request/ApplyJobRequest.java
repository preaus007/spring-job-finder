package com.touhed.hrm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobRequest {

	@NotNull
	private Long jobAdvertisementId;

    @NotNull
	private Long jobSeekerId;
}
