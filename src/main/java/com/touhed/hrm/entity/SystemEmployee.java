package com.touhed.hrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "system_employee" )
public class SystemEmployee {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@NotBlank( message = "First name cannot be blank." )
    @Size( min = 2, max = 100, message = "First name must be between 2 and 100 characters." )
    @Column( name = "first_name", nullable = false, length = 100 )
    private String firstName;

    @NotBlank( message = "Last name cannot be blank." )
    @Size( min = 2, max = 100, message = "Last name must be between 2 and 100 characters." )
    @Column( name = "lastName", nullable = false, length = 100 )
    private String lastName;

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || Hibernate.getClass( this ) != Hibernate.getClass( o ) ) return false;
        SystemEmployee systemEmployee = ( SystemEmployee ) o;
        return id != null && Objects.equals( id, systemEmployee.id );
    }

    @Override
    public int hashCode() {
        if ( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}
