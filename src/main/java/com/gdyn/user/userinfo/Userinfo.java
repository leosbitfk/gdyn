package com.gdyn.user.userinfo;

public class Userinfo {
    private String id=null;

    private String username=null;

    private String password=null;

    private String email=null;
    
    private String changeId=null;
    
    private String role=null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}