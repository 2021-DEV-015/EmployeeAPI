package com.bnppf.employee.api.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee {

	@Id
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "ADDRESS")
	private String address;

}
