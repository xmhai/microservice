package com.lin.common.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lin.common.core.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name = "idm_role")
public class Role extends BaseEntity {
    @Column(name = "role")
    private String name;
}
