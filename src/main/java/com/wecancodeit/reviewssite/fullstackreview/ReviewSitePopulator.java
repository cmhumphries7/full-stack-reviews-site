package com.wecancodeit.reviewssite.fullstackreview;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewSitePopulator implements CommandLineRunner {

	@Resource
	private ReviewRepo reviewRepo;

	@Resource
	private CategoryRepo categoryRepo;
	
	@Resource
	private TagRepo tagRepo;
	
	@Resource
	private CommentRepo commentRepo;

	@Override
	public void run(String... args) throws Exception {

		Category rpg = new Category("RPG",
				"A role-playing game (RPG) is a game in which players assume"
						+ " the roles of characters in a fictional setting.",
				"/images/skyrim1.jpg", "/images/skyrim2.jpg", "/images/skyrim3.png");
		rpg = categoryRepo.save(rpg);
		Category platformer = new Category("Platformer", "A platformer is a genre in which the player "
				+ "controls a character or avatar to jump between suspended" + " platforms and avoid obstacles.",
				"/images/cuphead1.jpg", "/images/cuphead2.jpg", "/images/cuphead3.jpg");
		platformer = categoryRepo.save(platformer);
		Category puzzleGame = new Category("Puzzle Game",
				"Puzzle games can test many problem-solving skills including"
						+ " logic, pattern recognition, sequence solving," + " and word completion.",
				"/images/fran1.jpg", "/images/fran2.jpg", "/images/fran3.jpg");
		puzzleGame = categoryRepo.save(puzzleGame);
		
		Tag puzzle = tagRepo.save(new Tag("puzzle"));
		Tag indie = tagRepo.save(new Tag("indie"));
		Tag platform = tagRepo.save(new Tag("platform"));
		Tag roleplay = tagRepo.save(new Tag("RPG"));
		Tag horror = tagRepo.save(new Tag("horror"));
		Tag runGun = tagRepo.save(new Tag("run-and-gun"));
		Tag pc = tagRepo.save(new Tag("PC"));
		Tag ninSwitch = tagRepo.save(new Tag("NintendoSwitch"));
		Tag xbox = tagRepo.save(new Tag("XboxOne"));
		Tag ps4 = tagRepo.save(new Tag("PlayStation4"));
		Tag vr = tagRepo.save(new Tag("VR"));
		

		Review skyrim = new Review("Skyrim", "/images/character.png",
				"The Elder Scrolls V: Skyrim is an action role-playing"
						+ " video game developed by Bethesda Game Studios " + "and published by Bethesda Softworks. "
						+ "The game's main story revolves around " + "the player character's "
						+ "quest to defeat Alduin the World-Eater, "
						+ "a dragon who is prophesied to destroy the world.",
				"http://elderscrolls.wikia.com/wiki/Skyrim", rpg, roleplay, pc, ninSwitch, xbox, ps4, vr);
		skyrim = reviewRepo.save(skyrim);

		Review cuphead = new Review("Cuphead", "/images/cuphead.jpg",
				"Cuphead is a run and gun " + "indie video game developed and published by StudioMDHR. "
						+ "The game was heavily inspired by " + "the rubber hose style of animation used in"
						+ " cartoons of the 1930s, such as the work of"
						+ " studios Fleischer and Walt Disney Animation, " + "seeking to emulate their subversive and "
						+ "surrealist qualities.",
				"http://cuphead.wikia.com/wiki/Cuphead_(game)", platformer, platform, runGun, pc, xbox);
		cuphead = reviewRepo.save(cuphead);

		Review franBow = new Review("Fran Bow", "/images/franbow.png",
				"Fran Bow is an indie psychological horror" + " adventure video game developed "
						+ "and published by Killmonday Games. " + "Gameplay involves seeking out various objects"
						+ " in the game world in order to combine and "
						+ "use them to solve puzzles, and speaking with " + "many characters to learn"
						+ " more about the world and how to progress.",
				"http://fran-bow.wikia.com/wiki/Fran_Bow_Dagenhart", puzzleGame, puzzle, indie, horror, pc);
		franBow = reviewRepo.save(franBow);
		
		//comments
		Comment comment1 = commentRepo.save(new Comment("I love Skyrim!! I can't wait for the new Elder Scrolls game.", "Snape", skyrim));
		Comment comment2 = commentRepo.save(new Comment("Cuphead is really challenging, but the art and music are really cool.", "Flitwick", cuphead));
		Comment comment3 = commentRepo.save(new Comment("Fran Bow is so dark. It really makes you feel like you're fighting insanity. ", "Peeves", franBow));
		Comment comment4 = commentRepo.save(new Comment("Mr. Midnight reminds me of my cat, Salem!", "Christine", franBow));
		

	}
}
