package com.springbootrestapi.model;


import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="TBL_EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="employee_id")
	private Long id;
	
	
	@Column(name="employee_name")
	@NotBlank(message="Name is not null")
	private String name;
	
	@Column(name="employee_age")
	private Long age=0L;
	
	@Column(name="employee_location")
	private String location;
	
	@Column(name="employee_email",unique = true)
	@Email(message="Please inter the email address")
	private String email;
	
	@Column(name = "employee_department")
	@NotBlank(message="Department is not null")
	private String department;
	
	@CreationTimestamp
	@Column(name="employee_create_at", nullable=false, updatable=false)
	private Date createdAt;
	
	@UpdateTimestamp	
	@Column(name="employee_update_at")
	private Date updateAt;

}
