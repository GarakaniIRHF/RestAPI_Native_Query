package com.garakaniirhf.spring.datajpa.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;

	  private String heading;

	  private String description;
	  
	  private int grade;

	  private boolean published;
	  
	  @Temporal(TemporalType.TIMESTAMP)
	  private Date createdAt;

	  public Book() {

	  }

	  public Book(String heading, String description, int grade, boolean published, Date createdAt) {
	    this.heading = heading;
	    this.description = description;
	    this.grade = grade;
	    this.published = published;
	    this.createdAt = createdAt;
	  }

	  public long getId() {
	    return id;
	  }

	  public String getHeading() {
	    return heading;
	  }

	  public void setHeading(String heading) {
	    this.heading = heading;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public int getGrade() {
	    return grade;
	  }

	  public void setGrade(int grade) {
	    this.grade = grade;
	  }

	  public boolean isPublished() {
	    return published;
	  }

	  public void setPublished(boolean isPublished) {
	    this.published = isPublished;
	  }

	  public Date getCreatedAt() {
	    return createdAt;
	  }

	  public void setCreatedAt(Date createdAt) {
	    this.createdAt = createdAt;
	  }

	  @Override
	  public String toString() {
	    return "Book [id=" + id + ", heading=" + heading + ", description=" + description + ", grade=" + grade + ", published=" + published + ", createdAt=" + createdAt + "]";
	  }
	}
