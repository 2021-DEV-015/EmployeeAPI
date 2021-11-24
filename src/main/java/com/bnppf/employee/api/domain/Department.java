package com.bnppf.employee.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DEPARTMENT")
@Data
@NoArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "EMPLOYEE_ID")
	private Employee employee;

	public Department(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
