package com.shopping.my.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Syed Irfan
 *
 */
@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6124983082734593301L;

	@Column(name="CREATED_BY")
	@CreatedBy
	protected  String createdBy;
	
	@Column(name="MODIFIED_BY")
	@LastModifiedBy
	protected  String modifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	@CreatedDate
	protected  Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MODIFIED_DATE")
	@LastModifiedDate
	protected  Date modifiedDate;
	
	@Column(name="IS_ACTIVE")
	protected Boolean isActive=Boolean.TRUE;

	
	
	

}
