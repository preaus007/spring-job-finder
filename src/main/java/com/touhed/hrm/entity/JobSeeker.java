package com.touhed.hrm.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "job_seekers" )
public class JobSeeker {
	
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "First name cannot be blank." )
    @Size( min = 2, max = 100, message = "First name must be between 2 and 100 characters." )
    @Column( name = "first_name", nullable = false, length = 100 )
    private String firstName;

    @NotBlank( message = "Last name cannot be blank." )
    @Size( min = 2, max = 100, message = "Last name must be between 2 and 100 characters." )
    @Column( name = "last_name", nullable = false, length = 100 )
    private String lastName;

    @NotBlank( message = "National ID cannot be blank." )
    @Size( min = 10, max = 20, message = "National ID must be between 10 and 20 characters." )
    @Column( name = "national_id", nullable = false, length = 20, unique = true )
    private String nationalId;

    @NotNull( message = "Birth date is required." )
    @Past( message = "Birth date must be in the past." )
    @Column( name = "birthDate", nullable = false )
    private LocalDate birthDate;

    @NotBlank( message = "Email cannot be blank." )
    @Email( message = "Invalid email format." )
    @Size( max = 180, message = "Email must be at most 180 characters." )
    @Column( name = "email", nullable = false, length = 180, unique = true )
    private String email;

    @JsonIgnore
    @NotBlank( message = "Password cannot be blank." )
    @Size( min = 6, max = 100, message = "Password must be between 6 and 100 characters." )
    @Column( name = "password", nullable = false, length = 100 )
    private String password;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        JobSeeker jobSeeker = ( JobSeeker ) o;
        return id != null && Objects.equals( id, jobSeeker.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}