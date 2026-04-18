package com.touhed.hrm.dto.request;

import com.touhed.hrm.enums.JobApplicationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class UpdateApplicationStatusRequest {
	
    @NotNull
    private Long applicationId;

    @NotNull
    private JobApplicationStatus status;
}
