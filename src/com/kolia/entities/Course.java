package com.kolia.entities;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	private String name;
	@Column(name="course_length")
	private int length;
	@Column(name="course_fee")
	private double tuitionFees;
	@Column(name="start_date")
	private Date startDate;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="course", cascade = CascadeType.ALL)
	private List<User> users;
	
	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}

	public Course(String name, int length, double fee, int year, int month, int dayOfMonth) {
		this.name = name;
		this.length = length;
		this.tuitionFees = fee;
		GregorianCalendar calendar = new GregorianCalendar(year, month -1, dayOfMonth);
		startDate = calendar.getTime();
	}
	public Course(int id, String name, int length, double fee, int year, int month, int dayOfMonth) {
		this.id = id;
		this.name = name;
		this.length = length;
		this.tuitionFees = fee;
		GregorianCalendar calendar = new GregorianCalendar(year, month -1, dayOfMonth);
		startDate = calendar.getTime();
	}

	public double getCourseFee() {
		return tuitionFees;
	}

	public void setCourseFee(double courseFee) {
		this.tuitionFees = courseFee;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
	
}
