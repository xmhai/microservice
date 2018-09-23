package com.lin.microservice.service.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.lin.common.core.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "idm_user")
public class User extends BaseEntity {
	@Column(name = "username")
	@NotEmpty(message = "*user name cannot be empty")
	private String userName;
	
	@Column(name = "password")
	@NotEmpty(message = "*password cannot be empty")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	@Email(message = "*Invalid email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "idm_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}