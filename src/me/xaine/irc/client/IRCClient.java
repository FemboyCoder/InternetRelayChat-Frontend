package me.xaine.irc.client;

import lombok.Getter;
import me.xaine.irc.util.ConsoleUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class IRCClient {

    @Getter
    private final Logger log = LogManager.getLogger("IRCClient");
    @Getter
    private final String CONNECTION_ADDRESS;
    @Getter
    private final int CONNCETION_PORT;
    @Getter
    private SocketChannel socketChannel = null;

    public IRCClient(final String address, final int port) {
        this.CONNECTION_ADDRESS = address;
        this.CONNCETION_PORT = port;
    }

    public void startup(String username, String password) {

        if (isSocketOpen()) {
           return;
        }

        ConsoleUtil.setConsoleTextColour(15);
        log.info("Starting IRCClient...");
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(CONNECTION_ADDRESS, CONNCETION_PORT));
            String send = "{\"type\": \"authentication\", \"data\": { \"username\": \"" + username + "\", \"password\": \"" + password + "\" }}";
            socketChannel.write(ByteBuffer.wrap(send.getBytes(StandardCharsets.UTF_8)));

            ByteBuffer responseBuffer = ByteBuffer.allocate(2048);
            int size = socketChannel.read(responseBuffer);
            String responsePayload = new String(responseBuffer.array()).substring(0, size);
            log.info("RESPONSE: " + responsePayload);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSocketOpen() {
        return socketChannel != null && socketChannel.isOpen();
    }

}
