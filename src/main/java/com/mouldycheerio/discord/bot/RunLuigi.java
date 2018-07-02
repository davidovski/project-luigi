package com.mouldycheerio.discord.bot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

public class RunLuigi {
    private static JSONObject config;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            config = new JSONObject();
            load();

            OuijaBot orangepeel = null;
            try {
                orangepeel = new OuijaBot(config.getString("token"), config.getString("prefix"));
            } catch (Exception e1) {
                // TODO Auto-generated catch block

                e1.printStackTrace();
                System.exit(0);
            }

            long a = 0;
            Scanner in = new Scanner(System.in);
            while (true) {

                try {
                    orangepeel.loop(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                a++;
                // Logger.raw(a + "");
                Thread.sleep(50);
                if (orangepeel.getStatus() == BotStatus.SHUTTINGDOWN) {
                    System.exit(0);
                }

                if (orangepeel.getStatus() == BotStatus.REBOOTING) {
                    System.out.println("RESTARTING!!");
                    break;
                }
            }
            orangepeel = null;


        }

        //
    }

    public static void load() {

        try {
            JSONTokener parser = new JSONTokener(new FileReader("config.json"));

            JSONObject obj = (JSONObject) parser.nextValue();

            config = obj;
            System.out.println(config);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
