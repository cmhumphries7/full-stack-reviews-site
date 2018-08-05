package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepo extends CrudRepository<Tag, Long> {

	Collection<Tag> findByReviews(Optional<Review> review);

	Optional<Tag> findFirstByName(String tagName);

}
