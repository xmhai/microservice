package com.lin.common.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class User extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	@NotEmpty(message = "*Please provide your user name")
	private String userName;
	
	@Column(name = "password")
	@NotEmpty(message = "*Please provide your password")
	private String password;
	
	@Column(name = "first_name")
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "idm_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}