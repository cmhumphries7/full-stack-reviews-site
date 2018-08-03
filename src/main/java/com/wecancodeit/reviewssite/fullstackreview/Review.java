package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	private Collection<Tag> tags;


	public long getId() {
		return id;
	}

	protected Review() {
		
	}
	
	public Review(String name, String image, String content, String link, Category category, Tag...tags) {
		this.name = name;
		this.image = image;
		this.content = content;
		this.link = link;
		this.category = category;
		this.tags = new HashSet<>(Arrays.asList(tags));
		
	
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
	
	public Collection<Tag> getTags() {
		return tags;
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