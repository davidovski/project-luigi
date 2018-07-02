package com.mouldycheerio.discord.bot.commands;

import com.mouldycheerio.discord.bot.OuijaBot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;

public class SetPlayingTextCommand extends AdminCommand {
    public SetPlayingTextCommand() {
        setName("setPlayingText");
        setDescription(new CommandDescription("Set Playing Text", "Set what the bot is playing", "setPlayingText <text>"));
        setCommandlvl(3);
    }

    public void onCommand(OuijaBot orangepeel, IDiscordClient client, IMessage commandMessage, String[] args) {
        StringBuilder sb = new StringBuilder();
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                if (i > 1) {
                    sb.append(" ");
                }
                sb.append(args[i]);
            }
        }
        String text = sb.toString();
        orangepeel.setPlayingText(text);
        orangepeel.saveAll();
        commandMessage.getChannel().sendMessage(":white_check_mark: Playing **" +orangepeel.getPlayingText() +"** :white_check_mark:");
    }
}
