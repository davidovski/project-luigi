package com.mouldycheerio.discord.bot;

import java.util.ArrayList;
import java.util.List;

import com.mouldycheerio.discord.bot.commands.AdminCommand;
import com.mouldycheerio.discord.bot.commands.Command;
import com.mouldycheerio.discord.bot.commands.GetLetterCommand;
import com.mouldycheerio.discord.bot.commands.HeyCommand;
import com.mouldycheerio.discord.bot.commands.ResponseTimeCommand;
import com.mouldycheerio.discord.bot.commands.ServersCommand;
import com.mouldycheerio.discord.bot.commands.SetChatChannelCommand;
import com.mouldycheerio.discord.bot.commands.SetPlayingTextCommand;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class CommandController {
    private List<Command> commands;
    private OuijaBot ouijaBot;

    public CommandController(OuijaBot ouijaBot) {
        this.ouijaBot = ouijaBot;
        commands = new ArrayList<Command>();
        commands.add(new ResponseTimeCommand());
        commands.add(new ServersCommand());
        commands.add(new HeyCommand());
        commands.add(new SetChatChannelCommand());
        commands.add(new SetPlayingTextCommand());
        commands.add(new GetLetterCommand());

    }

    public void onMessageReceivedEvent(MessageReceivedEvent event, String prefix) {
        String msg = event.getMessage().getContent();
        String[] parts = msg.split(" ");
        String commandname = parts[0].substring(prefix.length());
        for (Command c : commands) {
            if (commandname.equalsIgnoreCase(c.getName())) {
                if (c instanceof AdminCommand) {
                    if (ouijaBot.getAdmins().has(event.getAuthor().getStringID())) {
                        if (((AdminCommand) c).getCommandlvl() <= ouijaBot.getAdmins().getInt(event.getAuthor().getStringID())) {
                            c.onCommand(ouijaBot, ouijaBot.getClient(), event.getMessage(), parts);
                        } else {
                            event.getMessage().reply(((AdminCommand) c).getNoPermText());
                        }
                    } else {
                        event.getMessage().reply(((AdminCommand) c).getNoPermText());

                    }
                } else {
                    c.onCommand(ouijaBot, ouijaBot.getClient(), event.getMessage(), parts);
                }
            }
        }
    }

    public void addCommand(Command c) {
        commands.add(c);
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
