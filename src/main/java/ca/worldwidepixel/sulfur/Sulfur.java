package ca.worldwidepixel.sulfur;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sulfur implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("sulfur");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("DISCLAIMER: YOU'RE RUNNING A JOKE MOD! IF ANYONE HAS SENT THIS IN A SUPPORT CHANNEL, PLEASE TELL THEM ABOUT THAT OR IGNORE THE TROLL!");
		LOGGER.info("Your game will never be the same.");
	}
}
