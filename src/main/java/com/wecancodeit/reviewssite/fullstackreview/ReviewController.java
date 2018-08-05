package com.wecancodeit.reviewssite.fullstackreview;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReviewController {

	@Resource
	private CategoryRepo categoryRepo;

	@Resource
	private ReviewRepo reviewRepo;
	
	@Resource
	private TagRepo tagRepo;
	
	@Resource
	private CommentRepo commentRepo;

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);

		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "category";
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/show-categories")
	public String findAllCategories(Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		return ("categories");
	}

	@RequestMapping("/review")
	public String findOneReview(@RequestParam(value = "id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			// model.addAttribute("categories", review.get().getCategory());
			return "review";
		}
		throw new ReviewNotFoundException();

	}

	@RequestMapping("/show-reviews")
	public String findAllReviews(Model model) {
		model.addAttribute("reviews", reviewRepo.findAll());
		return ("reviews");
	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		
		if(tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return "tag";
		}
		throw new TagNotFoundException();
		
	}
	@RequestMapping("show-tags")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return "tags";
		
	}
	
	@PostMapping("/new-comment")
	public RedirectView createCommentForReview(
			@RequestParam(value="comment") String comment,
			@RequestParam(value="poster") String poster,
			@RequestParam(value="id") Long reviewId,
			Model model) {
		
		Optional<Review> reviewOptional = reviewRepo.findById(reviewId);
		
		
		if (reviewOptional.isPresent()) {
			Review review = reviewOptional.get();
			Comment createdComment = commentRepo.save(new Comment(comment, poster, review));
			reviewRepo.save(review);
		}
		return new RedirectView("/review?id=" + reviewId);
	}
}
