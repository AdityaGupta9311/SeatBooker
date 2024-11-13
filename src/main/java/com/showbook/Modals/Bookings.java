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
public class Bookings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private Users user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id", nullable = false)
	private Shows show;

	@Column(nullable = false)
	private int seats;

	@Column(nullable = false)
	private double total_amount;

	@Column(nullable = false)
	private String payment_status;

	@Column(nullable = false)
	private LocalDateTime booking_time;

	public Bookings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Shows getShow() {
		return show;
	}

	public void setShow(Shows show) {
		this.show = show;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public LocalDateTime getBooking_time() {
		return booking_time;
	}

	public void setBooking_time(LocalDateTime booking_time) {
		this.booking_time = booking_time;
	}

}
