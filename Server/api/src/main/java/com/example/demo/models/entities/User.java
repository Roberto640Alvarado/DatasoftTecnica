package com.example.demo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @Column(name = "id")
	 private Integer id;
	 
	 @Column(name = "full_name")
	 private String fullName;
	 
	 @Column(name = "username")
	 private String username;

	 @Column(name = "passwd")
	 private String password;
	 
	 @Column(name = "state", insertable = false)
	 private Boolean state;
	 
	 @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	 @JsonIgnore
	 private List<Token> tokens;
	 
	 @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
	 @JsonIgnore
	 private List<Books> booksUser; //conexion con books


		public User(Integer id, String fullName, String username, String password, Boolean state) {
			super();
			this.id =  id;
			this.fullName = fullName;
			this.username = username;
			this.password = password;
			this.state = state;
			
			
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
		}
		//getUsername is already overridden
		@Override
		public boolean isAccountNonExpired() {
		return false;
		}
		@Override
		public boolean isAccountNonLocked() {
		return false;
		}
		@Override
		public boolean isCredentialsNonExpired() {
		return false;
		}
		@Override
		public boolean isEnabled() {
		return this.state;
		}


}