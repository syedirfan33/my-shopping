/**
 * 
 */
package com.shopping.my.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Syed Irfan
 *
 */
import org.hibernate.annotations.NaturalId;

import com.shopping.my.enumeration.RoleName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@NoArgsConstructor
@Getter
@Setter
public class RoleEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2804001895395041392L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId 
	@Column(name="NAME",length = 60)
	private RoleName name;


	public RoleEntity(RoleName name) {
		this.name = name;
	}

}