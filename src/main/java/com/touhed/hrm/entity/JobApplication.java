package com.touhed.hrm.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.touhed.hrm.enums.JobApplicationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.Hibernate;

@Data 
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
	    name = "job_applications",
	    uniqueConstraints = @UniqueConstraint( columnNames = {"job_advertisement_id", "job_seeker_id"} )
	)
public class JobApplication {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "application_date", nullable = false )
    private LocalDateTime applicationDate = LocalDateTime.now();

    @NotNull
    @Enumerated( EnumType.STRING )
    @Column( name = "status", nullable = false, length = 20 )
    private JobApplicationStatus status = JobApplicationStatus.PENDING;

    @ManyToOne( optional = false )
    @JoinColumn( name = "job_advertisement_id", nullable = false )
    private JobAdvertisement jobAdvertisement;

    @ManyToOne( optional = false )
    @JoinColumn( name = "job_seeker_id", nullable = false )
    private JobSeeker jobSeeker;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        JobApplication jobApplication = ( JobApplication ) o;
        return id != null && Objects.equals( id, jobApplication.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}