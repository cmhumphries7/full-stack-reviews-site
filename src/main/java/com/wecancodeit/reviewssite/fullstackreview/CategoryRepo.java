package com.wecancodeit.reviewssite.fullstackreview;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {

	/*Collection<Category> findByReviewContains(Review review);

	Collection<Category> findByReviewId(long id);*/
}