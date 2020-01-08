package com.shopping.my.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PRODUCT")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductEntity extends BaseEntity{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5820838656529876471L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="URL")
	private String url;

	@Column(name="TITLE")
	private String title;
	
	@Column(name="IMAGE_URL")
	@ElementCollection
	@JoinTable(name = "PRODUCT_IMAGES", joinColumns = @JoinColumn(name = "PRD_ID"))
	private List<String> images = new ArrayList<String>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	private CategoryEntity ctgr;
	
	@Column(name="PRICE")
	private Long price;
	
	@Column(name="MSRP")
	private Long msrp;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private MerchantDetailEntity merchant;

	@Column(name="DESCRIPTION", length=2000)
	@Lob
	private String description;

	
}
