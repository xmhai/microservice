package com.lin.microservice.service.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lin.common.core.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "idm_role")
public class Role extends BaseEntity {
    @Column(name = "role")
    private String role;
}
