package com.mouldycheerio.discord.bot.commands;

import com.mouldycheerio.discord.bot.OuijaBot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

public class HeyCommand extends BotCommand {
    public HeyCommand() {
        setName("hey");
        setDescription(new CommandDescription("Hey", "Hey!", "hey"));
    }

    public void onCommand(OuijaBot orangepeel, IDiscordClient client, IMessage commandMessage, String[] args) {
        commandMessage.getChannel().sendMessage("**NO!** Go away!");

    }
}
