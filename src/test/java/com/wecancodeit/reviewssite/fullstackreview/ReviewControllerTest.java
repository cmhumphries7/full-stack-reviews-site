package com.wecancodeit.reviewssite.fullstackreview;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;

	@Mock
	private ReviewRepo reviewRepo;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private CategoryRepo categoryRepo;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1L;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));

		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("review", review);
	}

	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);

		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}

	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long arbitraryCategoryId = 1L;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));

		underTest.findOneCategory(arbitraryCategoryId, model);
		verify(model).addAttribute("category", category);
	}

	@Test
	public void shouldAddAllCategoriesToModel() throws CategoryNotFoundException {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);

		underTest.findAllCategories(model);
		verify(model).addAttribute("categories", allCategories);
	}
}
