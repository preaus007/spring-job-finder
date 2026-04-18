package com.touhed.hrm.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "job_positions", uniqueConstraints = @UniqueConstraint( columnNames = "title" ) )
public class JobPosition {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

    @NotBlank( message = "Job position title cannot be blank." )
    @Size( min = 2, max = 150, message = "Job position title must be between 2 and 150 characters." )
    @Column( name = "title", nullable = false, length = 150, unique = true )
	private String title;

	@JsonIgnore
	@OneToMany( mappedBy = "jobPosition", fetch = FetchType.LAZY )
	private List<JobAdvertisement> jobAdvertisements;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        JobPosition jobPosition = ( JobPosition ) o;
        return id != null && Objects.equals( id, jobPosition.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}
