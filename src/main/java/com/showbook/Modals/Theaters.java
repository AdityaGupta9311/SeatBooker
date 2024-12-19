package com.showbook.Modals;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Theaters {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	private String theater_name;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String total_screens;

	@Column(nullable = false)
	private String contact;

	@Column(nullable = false)
	private LocalDateTime created_at;

	@Column(nullable = false)
	private LocalDateTime updated_at;

	private String role = "ROLE_THEATER";

	public Theaters() {
		super();
	}

	public Theaters(long id, String theater_name, String email, String password, String location, String total_screens,
			String contact, LocalDateTime created_at, LocalDateTime updated_at, String role) {
		super();
		this.id = id;
		this.theater_name = theater_name;
		this.email = email;
		this.password = password;
		this.location = location;
		this.total_screens = total_screens;
		this.contact = contact;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTheater_name() {
		return theater_name;
	}

	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTotal_screens() {
		return total_screens;
	}

	public void setTotal_screens(String total_screens) {
		this.total_screens = total_screens;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	   @JsonCreator
//	    public Theaters(@JsonProperty("id") Long id) {
//	        this.id = id;
//	    }
}
