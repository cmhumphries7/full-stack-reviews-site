package com.wecancodeit.reviewssite.fullstackreview;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingsTest {

	@Resource
	private TestEntityManager entityManager;

	@Resource
	private CategoryRepo categoryRepo;

	@Resource
	private ReviewRepo reviewRepo;

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}

	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "description"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(categoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {

		Review review = reviewRepo.save(new Review("review"));
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getName(), is("review"));

	}

	@Test
	public void shouldEstablishCategoriesToReviewRelationship() {

		Category category = categoryRepo.save(new Category("RPG", "role-playing game"));
		long categoryId = category.getId();

		Review skyrim = reviewRepo.save(new Review("Skyrim", "dragon image", "content", category));
		long skyrimId = skyrim.getId();

		Review fallout = reviewRepo.save(new Review("Fallout 4", "fallout image", "content", category));
		long falloutId = fallout.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> categoryOptional = categoryRepo.findById(categoryId);
		Category resultCategory = categoryOptional.get();

		Optional<Review> skyrimOptional = reviewRepo.findById(skyrimId);
		Review resultSkyrim = skyrimOptional.get();

		Optional<Review> falloutOptional = reviewRepo.findById(falloutId);
		Review resultFallout = falloutOptional.get();

		assertThat(resultCategory.getReviews(), containsInAnyOrder(resultSkyrim, resultFallout));

	}
	

	@Test
	public void shouldFindReviewsForCategory() {
		Category category = categoryRepo.save(new Category("category", "description"));
		
		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage",
				"reviewContent", category));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImage",
				"reviewContent", category));
		
		Collection<Review> reviewsForCategory = reviewRepo.findByCategory(category);
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}

}
