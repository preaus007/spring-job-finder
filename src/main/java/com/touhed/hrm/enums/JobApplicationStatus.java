package com.touhed.hrm.enums;

import lombok.Getter;

@Getter
public enum JobApplicationStatus {
    PENDING("Pending" ),
    ACCEPTED( "Accepted" ),
    REJECTED( "Rejected" );

    private final String value;

    JobApplicationStatus( String value ) {
        this.value = value;
    }
}
