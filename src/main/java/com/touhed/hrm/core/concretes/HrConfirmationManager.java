package com.touhed.hrm.core.concretes;

import com.touhed.hrm.core.abstracts.HrConfirmationService;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessResult;
import com.touhed.hrm.entity.Employer;
import org.springframework.stereotype.Service;

@Service
public class HrConfirmationManager implements HrConfirmationService {

	@Override
	public Result isConfirm( Employer employer ) {
		return new SuccessResult( "accepted" );
	}
}
