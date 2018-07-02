package com.mouldycheerio.discord.bot;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.handle.obj.IChannel;

public class OuijaBot {
    private EventListener eventListener;
    private IDiscordClient client;
    public EventDispatcher dispatcher;

    private long uptime = 0;
    private long creation;

    private Random random;

    private JSONObject admins;
    private String playingText = "a game";
    private long playingtextindex = 0;
    private BotStatus status = BotStatus.INACTIVE;

    private JSONArray chatchannels;

    private ImageFactory fac;

    private String prefix;

    public OuijaBot(String token, String prefix) throws Exception {
        this.prefix = prefix;
        // ChatterBotFactory chatterBotFactory = new ChatterBotFactory();
        // chatterBot = chatterBotFactory.create(ChatterBotType.CLEVERBOT);
        // chatsession = chatterBot.createSession(Locale.ENGLISH);
        setFac(new ImageFactory());
        random = new Random();

        admins = new JSONObject();
        chatchannels = new JSONArray();
        status = BotStatus.ACTIVE;

        eventListener = new EventListener(prefix, this);

        creation = System.currentTimeMillis();
        client = ClientFactory.createClient(token, true);
        dispatcher = client.getDispatcher();
        dispatcher.registerListener(eventListener);

        loadAll();

    }

    public boolean isChatChannel(IChannel c) {
        for (int i = 0; i < chatchannels.length(); i++) {
            if (chatchannels.getString(i).equals(c.getStringID())) {
                return true;
            }
        }
        return false;
    }

    public void loadAll() {
        try {

            JSONTokener parser = new JSONTokener(new FileReader("Bot.opf"));

            JSONObject obj = (JSONObject) parser.nextValue();
            if (obj.has("admins")) {
                admins = obj.getJSONObject("admins");
            }

            if (obj.has("playing")) {
                playingText = obj.getString("playing");
            }

            if (obj.has("channels")) {
                chatchannels = obj.getJSONArray("channels");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAll() {
        JSONObject obj = new JSONObject();
        obj.put("admins", admins);
        obj.put("playing", playingText);
        obj.put("channels", chatchannels);


        try {
            FileWriter file = new FileWriter("Bot.opf");
            file.write(obj.toString(1));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loop(long alpha) throws InterruptedException {
        uptime = alpha;
        updatePlaying(alpha);

    }

    private void updatePlaying(long alpha) {
        if (alpha % 400 == 0 && client.isReady()) {
                client.changePlayingText(playingText);
        }
    }

    public IDiscordClient getClient() {
        return client;
    }

    public void setClient(IDiscordClient client) {
        this.client = client;
    }

    public long getUptime() {
        return System.currentTimeMillis() - creation;
    }

    public JSONObject getAdmins() {
        return admins;
    }

    public void setAdmins(JSONObject admins) {
        this.admins = admins;
    }

    public String getPlayingText() {
        return playingText;
    }

    public void setPlayingText(String playingText) {
        client.changePlayingText(playingText);
        this.playingText = playingText;
    }

    public BotStatus getStatus() {
        return status;
    }

    public void setStatus(BotStatus status) {
        this.status = status;
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public JSONArray getChatchannels() {
        return chatchannels;
    }

    public void setChatchannels(JSONArray chatchannels) {
        this.chatchannels = chatchannels;
    }

    public ImageFactory getFactory() {
        return fac;
    }

    public void setFac(ImageFactory fac) {
        this.fac = fac;
    }
}
