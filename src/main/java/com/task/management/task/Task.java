package com.task.management.task;

import com.task.management.group.Group;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table( name = "task" )
public class Task {
	
	@Id
	@GeneratedValue( strategy = GenerationType.UUID )
	private String id;

	@Column(name = "title")
	@NotEmpty
	private String title;

	@Column(name = "color")
	@NotEmpty
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
