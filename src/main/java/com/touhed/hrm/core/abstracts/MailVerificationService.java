package com.touhed.hrm.core.abstracts;

import com.touhed.hrm.core.utilities.Result;

public interface MailVerificationService {
	
	Result verifyEmail( String email );

}
