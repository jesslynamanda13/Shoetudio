package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private int id;
	private String name;
	private String username;
	private String email;
	private String password;
	private Boolean isAdmin;
	
	
	public User(int id, String name, String username, String email, String password, Boolean isAdmin) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public User(String name, String username, String email, String password, Boolean isAdmin) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}
	
	


	public void setUsername(String username) {
		this.username = username;
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
	
	public String getPasswordHash() {
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return String.format("%064x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}


	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
				+ password + ", isAdmin=" + isAdmin + "]";
	}

	

	
	
	
	
}
