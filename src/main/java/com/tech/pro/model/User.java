package com.tech.pro.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tech.pro.security.Role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document(collection = "user")
public class User implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private String email;

	private String password;

	private Boolean isAdmin;

	@CreatedDate
	private LocalDateTime timestamps;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> roles = new ArrayList<>();

		if (isAdmin) {
			roles.add(new SimpleGrantedAuthority(Role.ADMIN.getRole()));
		} else {
			roles.add((new SimpleGrantedAuthority(Role.USER.getRole())));
		}
		
		return roles;
	}

	public Role getRole() {
		return isAdmin ? Role.ADMIN : Role.USER;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
