package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String description;

	private String imageOne;

	private String imageTwo;

	private String imageThree;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImageOne() {
		return imageOne;
	}

	public String getImageTwo() {
		return imageTwo;
	}

	public String getImageThree() {
		return imageThree;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Category(String name, String description, String imageOne, String imageTwo, String imageThree,
			Review... reviews) {
		this.name = name;
		this.description = description;
		this.imageOne = imageOne;
		this.imageTwo = imageTwo;
		this.imageThree = imageThree;
		this.reviews = Arrays.asList(reviews);
	}

	public Category() {

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

}
