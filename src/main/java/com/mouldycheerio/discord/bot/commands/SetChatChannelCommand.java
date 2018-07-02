package com.mouldycheerio.discord.bot.commands;

import com.mouldycheerio.discord.bot.OuijaBot;
import com.mouldycheerio.discord.bot.PeelingUtils;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public class SetChatChannelCommand extends BotCommand {
    public SetChatChannelCommand() {
        setName("ouijaChannel");
        setDescription(new CommandDescription("ouijaChannel", "Set the chatting channel.", "ouijaChannel #channel"));
    }

    public void onCommand(OuijaBot bot, IDiscordClient client, IMessage commandMessage, String[] args) {
        if (commandMessage.getAuthor().getStringID().equals(commandMessage.getGuild().getOwner().getStringID())) {
            IChannel ch = PeelingUtils.channelMentionToId(args[1], commandMessage.getGuild());
            bot.getChatchannels().put(ch.getStringID());
            commandMessage.reply("luigi");
            bot.saveAll();
        }

    }
}
