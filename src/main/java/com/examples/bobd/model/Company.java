package com.examples.bobd.model;

import static jakarta.persistence.GenerationType.SEQUENCE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Entity
@Table(name = "companies", indexes = {
		@Index(columnList = "companyname")
})
@Value
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE) 
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class Company {
	
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	@NonFinal
	private Long id;
	@Column(name = "companyname", length = 50, unique=true,  nullable = false)
	String companyName;
}
