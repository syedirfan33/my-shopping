/**
 * 
 */
package com.shopping.my.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Syed Irfan
 *
 */
import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MERCHANT_DETAIL",uniqueConstraints = @UniqueConstraint(columnNames = { "MERCHANT_EMAIL" }))
@Data
@NoArgsConstructor
public class MerchantDetailEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2354245771299350708L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MERCHANT_NAME")
	private String name;

	@NaturalId
	@Column(name="MERCHANT_EMAIL", nullable = false)
	private String email;
}