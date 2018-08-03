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

		Review skyrim = new Review("Skyrim", "/images/character.png",
				"The Elder Scrolls V: Skyrim is an action role-playing"
						+ " video game developed by Bethesda Game Studios " + "and published by Bethesda Softworks. "
						+ "The game's main story revolves around " + "the player character's "
						+ "quest to defeat Alduin the World-Eater, "
						+ "a dragon who is prophesied to destroy the world.",
				"http://elderscrolls.wikia.com/wiki/Skyrim", rpg);
		skyrim = reviewRepo.save(skyrim);

		Review cuphead = new Review("Cuphead", "/images/cuphead.jpg",
				"Cuphead is a run and gun " + "indie video game developed and published by StudioMDHR. "
						+ "The game was heavily inspired by " + "the rubber hose style of animation used in"
						+ " cartoons of the 1930s, such as the work of"
						+ " studios Fleischer and Walt Disney Animation, " + "seeking to emulate their subversive and "
						+ "surrealist qualities.",
				"http://cuphead.wikia.com/wiki/Cuphead_(game)", platformer);
		cuphead = reviewRepo.save(cuphead);

		Review franBow = new Review("Fran Bow", "/images/franbow.png",
				"Fran Bow is an indie psychological horror" + " adventure video game developed "
						+ "and published by Killmonday Games. " + "Gameplay involves seeking out various objects"
						+ " in the game world in order to combine and "
						+ "use them to solve puzzles, and speaking with " + "many characters to learn"
						+ " more about the world and how to progress.",
				"http://fran-bow.wikia.com/wiki/Fran_Bow_Dagenhart", puzzleGame);
		franBow = reviewRepo.save(franBow);
	}
}
