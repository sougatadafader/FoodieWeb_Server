package com.example.cs5610_fall_2018_server_zhang_jawed_dafader.models;

import javax.persistence.Entity;

@Entity
public class SystemUser extends User{
	
	private boolean enabled = true;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

}
