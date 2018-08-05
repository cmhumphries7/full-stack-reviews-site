package com.wecancodeit.reviewssite.fullstackreview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	
    @Id
	@GeneratedValue
	private long id;
    
     @Lob
    private String comment;
     
    private String poster;
     
    @JsonIgnore
    @ManyToOne
    private Review review;
    
    //Constructors
    protected Comment() {
    	
    }
   
	public Comment(String comment, String poster, Review review) {
		this.comment = comment;
		this.poster = poster;
		this.review = review;
	}
	
	//Getters
	public long getId() {
		return id;
	}
	
	public String getComment() {
		return comment;
	}
	
	public String getPoster() {
		return poster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	
}
