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

	@Resource
	private TagRepo tagRepo;
	
	@Resource
	private CommentRepo commentRepo;

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertThat(category.getName(), is("category"));
	}

	@Test
	public void shouldGenerateCategoryId() {
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(categoryId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadReview() {

		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo
				.save(new Review("review", "reviewImage", "reviewContent", "reviewLink", category));
		long reviewId = review1.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review1 = result.get();
		assertThat(review1.getName(), is("review"));

	}

	@Test
	public void shouldEstablishCategoriesToReviewRelationship() {

		Category category = categoryRepo.save(new Category("RPG", "role-playing game", "image1", "image2", "image3"));
		long categoryId = category.getId();

		Review skyrim = reviewRepo.save(new Review("Skyrim", "dragon image", "content", "link", category));
		long skyrimId = skyrim.getId();

		Review fallout = reviewRepo.save(new Review("Fallout 4", "fallout image", "content", "link", category));
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
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo
				.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category));
		Review review2 = reviewRepo
				.save(new Review("reviewTitle2", "reviewImage", "reviewContent", "reviewLink", category));

		Collection<Review> reviewsForCategory = reviewRepo.findByCategory(category);
		assertThat(reviewsForCategory, containsInAnyOrder(review1, review2));
	}

	@Test
	public void shouldSaveAndLoadTags() {
		Tag tag = tagRepo.save(new Tag("Tag Name"));
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("Tag Name"));
	}

	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("Tag Name"));
		long tagId = tag.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(tagId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishReviewToTagRelationships() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		Tag tag2 = tagRepo.save(new Tag("tag2"));

		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category, tag1, tag2));
		long reviewId = review.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertThat(review.getTags(), containsInAnyOrder(tag1,tag2));
		
		}
	
	@Test
	public void shouldFindReviewsForTag() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category, tag1));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImage", "reviewContent", "reviewLink", category, tag1));

		Collection<Review> reviewsForTag = reviewRepo.findByTags(tag1);
		
		assertThat(reviewsForTag, containsInAnyOrder(review1, review2));
	}
	
	@Test
	public void shouldFindReviewsForTagId() {
		Tag tag1 = tagRepo.save(new Tag("tag1"));
		long tagId = tag1.getId();
		
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category, tag1));
		Review review2 = reviewRepo.save(new Review("reviewTitle2", "reviewImage", "reviewContent", "reviewLink", category, tag1));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewsForTopic = reviewRepo.findByTagsId(tagId);
		
		assertThat(reviewsForTopic, containsInAnyOrder(review1, review2));
		
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category));
		
		Comment comment = commentRepo.save(new Comment("comment", "poster",  review1));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> result = commentRepo.findById(commentId);
		comment = result.get();
		
		assertThat(comment.getComment(), is("comment"));
		
		}
	
	@Test
	public void shouldGenerateCommentId() {
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category));
		
		Comment comment = commentRepo.save(new Comment("comment", "poster",  review1));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(commentId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishCommentToReviewRelationship() {
		Category category = categoryRepo.save(new Category("category", "description", "image1", "image2", "image3"));

		Review review1 = reviewRepo.save(new Review("reviewTitle1", "reviewImage", "reviewContent", "reviewLink", category));
		
		Comment comment1 = commentRepo.save(new Comment("comment1", "poster",  review1));
		Comment comment2 = commentRepo.save(new Comment("comment2", "poster",  review1));
		
		long reviewId = review1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Review> result = reviewRepo.findById(reviewId);
		review1 = result.get();
		assertThat(review1.getComments(), containsInAnyOrder(comment1, comment2));
	}

}
