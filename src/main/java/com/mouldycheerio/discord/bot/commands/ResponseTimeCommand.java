package com.mouldycheerio.discord.bot.commands;

import com.mouldycheerio.discord.bot.OuijaBot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

public class ResponseTimeCommand extends BotCommand {
    public ResponseTimeCommand() {
        setName("ping");
        setDescription(new CommandDescription("Ping", "Find out how fast the bots running", "ping"));
    }

    public void onCommand(OuijaBot orangepeel, IDiscordClient client, IMessage commandMessage, String[] args) {
        commandMessage.getChannel().sendMessage("I like ping pong too. (" + (commandMessage.getTimestamp().getNano() / 1000000) + "ms)");

    }
}
