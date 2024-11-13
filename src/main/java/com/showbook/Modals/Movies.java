package com.showbook.Modals;

import java.time.LocalDate;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Movies {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private ArrayList<String> genre;

	@Column(nullable = false)
	private ArrayList<String> language;

	@Column(nullable = false)
	private String duration;

	@Column(nullable = false)
	private LocalDate release_date;

	@Column(nullable = false)
	private Double rating;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String poster;

	public Movies() {
		super();
		// TODO Auto-generated constructor stub
	}

//	@JsonCreator
//	public Movies(@JsonProperty("id") Long id) {
//		this.id = id;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

	public ArrayList<String> getLanguage() {
		return language;
	}

	public void setLanguage(ArrayList<String> language) {
		this.language = language;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public LocalDate getRelease_date() {
		return release_date;
	}

	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

}
