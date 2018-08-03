package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review, Long> {

	Collection<Review> findByCategory(Category category);

	Collection<Review> findByTags(Tag tag);

	Collection<Review> findByTagsId(long tagId);


}
