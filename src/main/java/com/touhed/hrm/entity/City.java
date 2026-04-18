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
@Table( name = "cities", uniqueConstraints = @UniqueConstraint( columnNames = "city_name" ) )
public class City {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "City name cannot be blank." )
    @Size( min = 2, max = 100, message = "City name must be between 2 and 100 characters." )
    @Column( name = "city_name", nullable = false, length = 100, unique = true )
    private String cityName;

    @JsonIgnore
    @OneToMany( mappedBy = "city", fetch = FetchType.LAZY )
    private List<JobAdvertisement> jobAdvertisements;

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if( o == null || Hibernate.getClass(this) != Hibernate.getClass( o ) ) return false;
        City city = ( City ) o;
        return id != null && Objects.equals( id, city.id );
    }

    @Override
    public int hashCode() {
        if( this.id == null )
            return System.identityHashCode( this );
        return Objects.hash( this.id );
    }
}