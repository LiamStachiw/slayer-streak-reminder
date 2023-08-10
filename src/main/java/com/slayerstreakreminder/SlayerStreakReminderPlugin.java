package com.slayerstreakreminder;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@PluginDescriptor(
	name = "Slayer Streak Reminder"
)
public class SlayerStreakReminderPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SlayerStreakReminderConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Slayer Streak Reminder started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Slayer Streak Reminder stopped!");
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage) {

		if (chatMessage.getType() == ChatMessageType.GAMEMESSAGE &&
				(chatMessage.getMessage().contains("; return to a Slayer master.") ||
				 chatMessage.getMessage().contains("You'll be eligible to earn reward points if you complete tasks from a more advanced Slayer Master."))){

			Matcher matcher = Pattern.compile("\\d+").matcher(chatMessage.getMessage());

			if(matcher.find()){
				int taskNum = Integer.parseInt(matcher.group());

                if(config.booleanConfig()) client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "******************************************************", null);
                //TODO: Fix the taskNum calculation
				if((taskNum + 1) % 10 == 0){
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Your next slayer task will give bonus points. Make sure you go to the correct Slayer master!", null);
                } else {
                    client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Your next slayer task will NOT give bonus points. Get fucked nerd.", null);
                }
                if(config.booleanConfig()) client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "******************************************************", null);
            }

		}
	}

	@Provides
	SlayerStreakReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SlayerStreakReminderConfig.class);
	}
}
