package com.rab3.dao.entity;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class ProfileEntity {
	
	private int aid;
	private String username;
	private String password;
	private String name;
	private String email;
	private String gender;
	private MultipartFile photo;
	private Timestamp doe;
	private String role;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public Timestamp getDoe() {
		return doe;
	}
	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ProfileEntity [aid=" + aid + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", gender=" + gender + ", photo=" + photo + ", doe=" + doe + ", role=" + role
				+ "]";
	}
	

}
