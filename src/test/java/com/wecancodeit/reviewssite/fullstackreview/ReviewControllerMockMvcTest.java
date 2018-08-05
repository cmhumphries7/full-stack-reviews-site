package com.wecancodeit.reviewssite.fullstackreview;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {

	@Resource
	private MockMvc mvc;

	@MockBean
	private CategoryRepo categoryRepo;

	@MockBean
	private ReviewRepo reviewRepo;
	
	@MockBean
	private TagRepo tagRepo;

	@Mock
	private Category category;

	@Mock
	private Category anotherCategory;

	@Mock
	private Review review;

	@Mock
	private Review anotherReview;
	
	@Mock
	private Tag tag;
	
	@Mock 
	private Tag anotherTag;

	@Test
	public void shouldRouteToSingleReviewView() throws Exception {
		long arbitraryReviewId = 42;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=42")).andExpect(view().name(is("review")));
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		long arbitraryReviewId = 42;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=42")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleReview() throws Exception {
		mvc.perform(get("/review?id=42")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleReviewIntoModel() throws Exception {
		when(reviewRepo.findById(42L)).thenReturn(Optional.of(review));
		mvc.perform(get("/review?id=42")).andExpect(model().attribute("review", is(review)));
	}
	
	@Test
	public void shouldRouteToAllReviewsView() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(view().name(is("reviews")));
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
		mvc.perform(get("/show-reviews")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllReviewsIntoModel() throws Exception {
		Collection<Review> allReviews = Arrays.asList(review, anotherReview);
		when(reviewRepo.findAll()).thenReturn(allReviews);
		
		mvc.perform(get("/show-reviews")).andExpect(model().attribute("reviews", is(allReviews)));
	}
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));

	}

	@Test
	public void shouldBeOkForASingleCategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkForASingleCategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

		mvc.perform(get("/category?id=1")).andExpect(model().attribute("category", is(category)));
	}

	@Test
	public void shouldRouteToAllCategoriesView() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(view().name(is("categories")));
	}

	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/show-categories")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, anotherCategory);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		mvc.perform(get("/show-categories")).andExpect(model().attribute("categories", is(allCategories)));
	}

	@Test
	public void shouldRouteToSingleTagView() throws Exception {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}

	@Test
	public void shouldBeOkForSingleTag() throws Exception {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkForSingleTag() throws Exception {
		mvc.perform(get("/tag?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleTagIntoModel() throws Exception {
		when(tagRepo.findById(1L)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tags", is(tag)));
	}

	@Test
	public void shouldRouteToAllTagsView() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(view().name(is("tags")));
	}

	@Test
	public void shouldBeOkForAllTags() throws Exception {
		mvc.perform(get("/show-tags")).andExpect(status().isOk());
	}

	@Test
	public void shouldPutAllTagsIntoModel() throws Exception {
		Collection<Tag> allTags = Arrays.asList(tag, anotherTag);
		when(tagRepo.findAll()).thenReturn(allTags);

		mvc.perform(get("/show-tags")).andExpect(model().attribute("tags", is(allTags)));
	}
	

}
