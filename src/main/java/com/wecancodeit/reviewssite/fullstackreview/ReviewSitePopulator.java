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

	@Override
	public void run(String... args) throws Exception {

		Category rpg = new Category("RPG",
				"A role-playing game in which players assume"
				+ " the roles of characters in a fictional setting.");
		rpg = categoryRepo.save(rpg);
		Category platformer = new Category("Platformer",
				"A platformer is a genre in which the player "
				+ "controls a character or avatar to jump between suspended"
						+ " platforms and avoid obstacles.");
		platformer = categoryRepo.save(platformer);
		Category puzzleGame = new Category("Puzzle Game",
				"Puzzle games can test many problem-solving skills including"
				+ " logic, pattern recognition, sequence solving,"
						+ " and word completion.");
		puzzleGame = categoryRepo.save(puzzleGame);
		


		Review skyrim = new Review("Skyrim", "/images/character.png",
				"The Elder Scrolls V: Skyrim is an action role-playing"
				+ " video game developed by Bethesda Game Studios "
						+ "and published by Bethesda Softworks. "
						+ "The game's main story revolves around "
						+ "the player character's "
						+ "quest to defeat Alduin the World-Eater, "
						+ "a dragon who is prophesied to destroy the world.",
				rpg);
		skyrim = reviewRepo.save(skyrim);

		Review cuphead = new Review("Cuphead", "/images/cuphead.png",
				"Cuphead is a run and gun "
						+ "indie video game developed and published by StudioMDHR. "
						+ "The game was heavily inspired by "
						+ "the rubber hose style of animation used in"
						+ " cartoons of the 1930s, such as the work of"
						+ " studios Fleischer and Walt Disney Animation, "
						+ "seeking to emulate their subversive and "
						+ "surrealist qualities. ",
				platformer);
		cuphead = reviewRepo.save(cuphead);

		Review franBow = new Review("Fran Bow", "/images/franbow.png",
				"Fran Bow is an indie psychological horror"
						+ " adventure video game developed "
						+ "and published by Killmonday Games."
						+ "Gameplay involves seeking out various objects"
						+ " in the game world in order to combine and "
						+ "use them to solve puzzles, and speaking with "
						+ "many characters to learn"
						+ " more about the world and how to progress.",
				puzzleGame);
		franBow = reviewRepo.save(franBow);
	}
}
