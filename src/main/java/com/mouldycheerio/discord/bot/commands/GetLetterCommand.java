package com.mouldycheerio.discord.bot.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.mouldycheerio.discord.bot.OuijaBot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

public class GetLetterCommand extends BotCommand {
    public GetLetterCommand() {
        setName("letter");
        setDescription(new CommandDescription("letter", "letter!", "letter"));
    }

    public void onCommand(OuijaBot bot, IDiscordClient client, IMessage commandMessage, String[] args) {
        String letter = args[1];
        if (letter == null) {
            letter = "a";
        }
        try {
            commandMessage.getChannel().sendFile(bot.getFactory().makeImage(letter));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
