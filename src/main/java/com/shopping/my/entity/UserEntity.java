package com.shopping.my.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.shopping.my.enumeration.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }),
		@UniqueConstraint(columnNames = { "EMAIL" }) })
public class UserEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6753792262973237768L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="NAME", nullable = false)
	private String name;

	@Column(name="USER_NAME", nullable = false)
	private String username;

	@Column(name="EMAIL", nullable = false)
	@NaturalId
	private String email;

	@Column(name="PASSWORD", nullable = false)
	@Size(max = 100)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<RoleEntity> roles = new HashSet<>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS",length = 10)
	private UserStatus status;
	
	@OneToMany
	private List<AccessTokenEntity> tokenList;
	
	public UserEntity(String name, String username, String email, String password, UserStatus status) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
    }
}
