package com.wecancodeit.reviewssite.fullstackreview;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String image;
	
	@Lob
	private String content;
	
	@ManyToOne
	private Category category;

	private String link;


	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getLink() {
		return link;
	}

	public Category getCategory() {
		return category;
	}
	public Review(String name, String image, String content, String link, Category category) {
		this.name = name;
		this.image = image;
		this.content = content;
		this.link = link;
		this.category = category;
		
	}

	public Review(String name) {
		this.name = name;
	}
	public Review() {
		
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}