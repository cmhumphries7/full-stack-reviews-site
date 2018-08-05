package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

	@Resource
	private ReviewRepo reviewRepo;
	
	@Resource
	private TagRepo tagRepo;
	
	@GetMapping("/{id}/tags")
	public Iterable<Tag> findAllTagsByReview(
			@PathVariable("id") Long reviewId) {
		Optional<Review> review = reviewRepo.findById(reviewId);
		return tagRepo.findByReviews(review);
	}
	
	@PutMapping("/add-new-tag")
	public Iterable<Tag> addNewReviewTag(
			@RequestBody TagUpdateRequest addNewTag
		){
		
		Optional<Review> reviewOptional = reviewRepo.findById(addNewTag.reviewId);
		
		
		if (reviewOptional.isPresent()) {
			Optional<Tag> tagOptional = tagRepo.findFirstByName(addNewTag.tagName);
			Tag createdTag;
			
			if(!tagOptional.isPresent()) {
				createdTag = tagRepo.save(new Tag(addNewTag.tagName));
			} else {
				createdTag = tagOptional.get();
			}
			
			Review review = reviewOptional.get();
			Collection<Tag> reviewTags = review.getTags();
			
			if (!reviewTags.contains(createdTag)) {
				reviewTags.add(createdTag);
				reviewRepo.save(review);
			}	

		}
		return tagRepo.findByReviews(reviewOptional);
	}
	
	@DeleteMapping("/removetag")
	public Iterable<Tag> removeTag (
			@RequestBody TagUpdateRequest removeTag
			){
		Optional<Tag> tagOptional = tagRepo.findFirstByName(removeTag.tagName);
		Tag tag = tagOptional.get();
		Optional<Review> reviewOptional = reviewRepo.findById(removeTag.reviewId);
		Review review = reviewOptional.get();
		
		try {
			review.deleteTag(tag);
			reviewRepo.save(review);
		} catch (Exception e) {
			//does not exist
		}
		return tagRepo.findByReviews(reviewOptional);
	}
}
