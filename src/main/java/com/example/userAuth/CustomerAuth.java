package com.example.userAuth;

public class CustomerAuth {
	
	String email;
	String password;

	public CustomerAuth() {
	}

	public CustomerAuth(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerAuth [email=" + email + ", password=" + password + "]";
	}

}
