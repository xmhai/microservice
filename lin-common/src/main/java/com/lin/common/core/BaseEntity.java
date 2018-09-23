package com.lin.common.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

    @Column
    protected Long createdBy;

    @Column
    protected String createdByUserName;

    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    protected Date createdDate;

    @Column
    protected Long updatedBy;

    @Column
    protected String updatedByUserName;

    @DateTimeFormat
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    protected Date updatedDate;

    @Version
    protected int version;
    
    @Column
    protected EntityState entityState;
}
