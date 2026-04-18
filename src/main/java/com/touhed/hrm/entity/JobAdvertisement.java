package com.touhed.hrm.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "job_advertisement" )
public class JobAdvertisement {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@NotNull( message = "Open position count is required." )
	@Positive( message = "Open position count must be positive." )
	@Column( name = "open_position_count", nullable = false )
	private Integer openPositionCount;

	@NotBlank( message = "Description cannot be blank." )
	@Size( min = 10, max = 5000, message = "Description must be between 10 and 5000 characters." )
	@Column( name = "description", nullable = false, length = 5000 )
	private String description;

	@PositiveOrZero( message = "Min salary must be 0 or greater." )
	@Column( name = "min_salary" )
	private Double minSalary;

	@PositiveOrZero( message = "Max salary must be 0 or greater." )
	@Column( name = "max_salary" )
	private Double maxSalary;

	@NotNull( message = "Release date is required." )
	@Column( name = "job_relase_date", nullable = false )
	private LocalDateTime releaseDate = LocalDateTime.now();

	@NotNull( message = "Application deadline is required." )
	@Future( message = "Application deadline must be in the future." )
	@Column( name = "application_deadline", nullable = false )
	private LocalDateTime applicationDeadline;

    @Column( name = "active", nullable = false )
    private boolean active = true;

	@NotNull( message = "Job position is required." )
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	@JoinColumn( name = "job_position_id", nullable = false )
	private JobPosition jobPosition;

	@NotNull( message = "City is required." )
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	@JoinColumn( name = "city_id", nullable = false )
	private City city;

	@NotNull( message = "Employer is required." )
	@ManyToOne( fetch = FetchType.LAZY, optional = false )
	@JoinColumn( name = "employer_id", nullable = false )
	private Employer employer;

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        JobAdvertisement jobAdvertisement = ( JobAdvertisement ) o;
        return id != null && Objects.equals( id, jobAdvertisement.id );
    }

    @Override
    public int hashCode() {
        if( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}