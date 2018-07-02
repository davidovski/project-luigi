package com.mouldycheerio.discord.bot;

import java.util.HashMap;
import java.util.Locale;

import com.google.code.chatterbotapi.ChatterBot;
import com.google.code.chatterbotapi.ChatterBotFactory;
import com.google.code.chatterbotapi.ChatterBotSession;
import com.google.code.chatterbotapi.ChatterBotType;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

public class EventListener {
    private String prefix;
    private OuijaBot ouijaBot;
    private CommandController commandController;
    private String ourmention;
    private String ourmention2;
    private ChatterBot bot1;
    private ChatterBotSession bot1session;

    private HashMap<String, ChatterBotSession> sessions;

    public EventListener(String prefix, OuijaBot ouijaBot) {
        this.prefix = prefix;
        this.ouijaBot = ouijaBot;
        commandController = new CommandController(ouijaBot);
        ChatterBotFactory factory = new ChatterBotFactory();
        sessions = new HashMap<String, ChatterBotSession>();
        try {
            bot1 = factory.create(ChatterBotType.PANDORABOTS, "f6d4afd83e34564d");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

    @EventSubscriber
    public void onUserJoinEvent(UserJoinEvent event) {
//        event.getGuild().getGeneralChannel().sendMessage("Hi, <@" + event.getUser().getStringID() + "> and welcome to " + event.getGuild().getName());
    }

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {

        IUser user = event.getClient().getOurUser();
        Logger.raw("==========");
        Logger.raw(user.getName() + "#" + user.getDiscriminator());
        Logger.raw(user.getStringID());
        Logger.raw("==========");
        ourmention = "<@" + ouijaBot.getClient().getOurUser().getStringID() + ">";
        ourmention2 = "<@!" + ouijaBot.getClient().getOurUser().getStringID() + ">";

    }

    @EventSubscriber
    public void onMessageReceivedEvent(MessageReceivedEvent event) throws Exception {
        String content = event.getMessage().getContent();


        if (ouijaBot.isChatChannel(event.getChannel())) {
            ai(event.getAuthor(), content, event.getChannel());
        }

        if (content.startsWith(prefix)) {
            commandController.onMessageReceivedEvent(event, prefix);
        }
    }

    public void ai(IUser user, String message, IChannel reply) {
        try {
            if (!sessions.containsKey(user.getStringID())) {
                    sessions.put(user.getStringID(), bot1.createSession(Locale.UK));
            }
            ChatterBotSession session = sessions.get(user.getStringID());
            String think = session.think(message);
            reply.sendMessage(user.mention() + " " + think);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public CommandController getCommandController() {
        return commandController;
    }

    public void setCommandController(CommandController commandController) {
        this.commandController = commandController;
    }
}
