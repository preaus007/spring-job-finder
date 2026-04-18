package com.touhed.hrm.core.abstracts;


import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.entity.Employer;

public interface HrConfirmationService {
	
	Result isConfirm( Employer employer );

}
