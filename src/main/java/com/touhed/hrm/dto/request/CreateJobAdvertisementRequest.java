package com.touhed.hrm.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobAdvertisementRequest {

	@NotNull
	private Long jobPositionId;

	@NotNull
	private Long cityId;

	@NotNull
	private Long employerId;

	@NotBlank
	@Size( min = 10, max = 5000 )
	private String description;

	@NotNull
	@Positive
	private Integer openPositionCount;

	@PositiveOrZero
	private Double minSalary;

	@PositiveOrZero
	private Double maxSalary;

	@NotNull
	@Future
	private LocalDateTime applicationDeadline;
}
