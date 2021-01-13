package com.swiftdroid.posterhouse.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mst_user_type")
public class Role {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private int roleId;
	
	@Column(name = "UTN")
	private String name;
	
	@Column(name="IA")
	private boolean enabled = true;
	
	@Column(name = "UTD")
	private String usertypediscription;

	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL, fetch=FetchType. LAZY)
	private Set<UserRole> userRoles = new HashSet<>();

	
	
	
	
	
	
	
	
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsertypediscription() {
		return usertypediscription;
	}

	public void setUsertypediscription(String usertypediscription) {
		this.usertypediscription = usertypediscription;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	

}