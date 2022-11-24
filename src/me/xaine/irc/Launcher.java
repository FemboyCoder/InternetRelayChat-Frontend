package me.xaine.irc;

import me.xaine.irc.client.IRCClient;
import me.xaine.irc.util.ConsoleUtil;

public class Launcher {

    public static void main(String[] args) {

        IRCClient client = new IRCClient("localhost", 8888);
        client.startup("testUsername", "testPassword");

    }

}