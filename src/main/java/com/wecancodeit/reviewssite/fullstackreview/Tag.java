package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long tagId;
	
	private String name;
	
	@ManyToMany(mappedBy ="tags")
	private Collection<Review> reviews;
	
	//Constructors
	protected Tag() {
		
	}
	
	public Tag(String name) {
		this.name = name;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public long getTagId() {
		return tagId;
	}
	
	public Collection<Review> getReviews() {
		return reviews;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tagId ^ (tagId >>> 32));
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
		Tag other = (Tag) obj;
		if (tagId != other.tagId)
			return false;
		return true;
	}
	
	
	
}
