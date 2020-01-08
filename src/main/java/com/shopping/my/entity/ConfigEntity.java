package com.shopping.my.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Syed Irfan
 *
 */
@Table(name = "APP_CONFIG")
@Entity
@Setter
@Getter
@ToString
public class ConfigEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4668849609843662843L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "C_KEY", nullable = false,unique=true)	
	private String configKey;

	@Column(name = "C_VALUE", nullable = false,length=1000)
	private String configValue;
	
	@Column(name = "C_DESCRIPTION",length=100)
	private String descritpyion;
	
	

	
}
