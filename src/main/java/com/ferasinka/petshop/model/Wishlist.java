package com.ferasinka.petshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;

public class Wishlist implements Serializable {
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("is_done")
	private boolean isDone;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean done) {
		isDone = done;
	}
	
	@Override
	public String toString() {
		return "Wishlist: " +
				"id = " + id +
				", description = " + description +
				", date = " + date +
				", isDone = " + isDone;
	}
}
