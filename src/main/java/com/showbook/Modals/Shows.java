package com.showbook.Modals;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Shows {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id", nullable = false)
	private Movies movie;

	@Column(nullable = false)
	private String duration;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "theater_id", nullable = false)
	private Theaters theater;

	@Column(nullable = false)
	private String show_time;
//
	@Column(nullable = false)
	private double priceperseat;

	@Column(nullable = false)
	private int available_seats;

	@Column(nullable = false)
	private int screenNumber;

	@Column(nullable = false)
	private String age_limit;

	@Column(nullable = false)
	private LocalDateTime created_at;

	@Column(nullable = false)
	private LocalDateTime updated_at;

	public Shows() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getPriceperseat() {
		return priceperseat;
	}

	public void setPriceperseat(double priceperseat) {
		this.priceperseat = priceperseat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Theaters getTheater() {
		return theater;
	}

	public void setTheater(Theaters theater) {
		this.theater = theater;
	}

	public String getShow_time() {
		return show_time;
	}

	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public int getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(int screenNumber) {
		this.screenNumber = screenNumber;
	}

	public String getAge_limit() {
		return age_limit;
	}

	public void setAge_limit(String age_limit) {
		this.age_limit = age_limit;
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

}
