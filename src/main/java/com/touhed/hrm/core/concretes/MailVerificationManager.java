package com.touhed.hrm.core.concretes;

import com.touhed.hrm.core.abstracts.MailVerificationService;
import com.touhed.hrm.core.utilities.Result;
import com.touhed.hrm.core.utilities.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class MailVerificationManager implements MailVerificationService {

    @Override
    public Result verifyEmail(String email) {
        return new SuccessResult( "Email verified" );
    }
}
