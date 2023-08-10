package com.slayerstreakreminder;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("slayerstreakreminder")
public interface SlayerStreakReminderConfig extends Config
{
	@ConfigItem(
            position = 1,
            keyName = "booleanConfig",
            name = "Add borders to message",
            description = "Add a line of stars before and after the message to make it more noticeable?"
	)
    default boolean booleanConfig(){ return true; }
}
