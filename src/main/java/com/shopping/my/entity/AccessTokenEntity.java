package com.shopping.my.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.shopping.my.enumeration.TokenStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ACCESS_TOKEN")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class AccessTokenEntity extends BaseEntity{
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5820838656529876471L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@Column(name="ACCESS_TOKEN",length=2000)
	private String accessToken;

	@Column(name="ACCESS_TOKEN_EXPIRY")
	@Temporal(TemporalType.TIME)
	private Date accessTokenExpiry;
	
	@Column(name="CHANNEL_CODE")
	private String channelCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private TokenStatus tokenStatus;
	
	@Column(name="USER_NAME")
	private String username;
	
	@ManyToOne()
	@JoinColumn(name="USER_ID",nullable=false)
	private UserEntity userid;

	@Column(name="CLIENT_IP")
	private String clientIp;

	
}
