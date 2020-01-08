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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Syed Irfan
 *
 */
@Table(name = "CTGR_LIST")
@Entity
@Data
@NoArgsConstructor
public class CategoryEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1138971638709834258L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CTGR_NAME", nullable = false)	
	private String ctgrName;

	@Column(name = "CTGR_LEVEL", nullable = false)
	private int ctgrLevel;
	
	@Column(name = "CTGR1_NO")
	private Long ctgrNo1;
	
	@Column(name = "CTGR2_NO")
	private Long ctgrNo2;
	
	@Column(name = "CTGR3_NO")
	private Long ctgrNo3;
	
	@Column(name = "CTGR1_NAME")
	private String ctgrName1;
	
	@Column(name = "CTGR2_NAME")
	private String ctgrName2;
	
	@Column(name = "CTGR3_NAME")
	private String ctgrName3;
	
	@Column(name = "CTGR_CODE")
	private String ctgrCode;
}
