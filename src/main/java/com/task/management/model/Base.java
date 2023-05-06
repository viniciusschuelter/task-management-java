package com.task.management.model;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Base implements Serializable {	

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

}
