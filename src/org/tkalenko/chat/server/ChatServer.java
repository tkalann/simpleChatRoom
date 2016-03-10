package org.tkalenko.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by tkalenko on 09.03.2016.
 */
public class ChatServer implements Runnable {

    private ServerSocket serverSocket;
    private Collection<ChatClient> clients = new HashSet<ChatClient>();

    /**
     * Создание сервера
     *
     * @param port порт
     * @throws IllegalArgumentException если порт занят или с ним проблема
     */
    public ChatServer(final int port) throws IllegalArgumentException {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("wrong port={%s}", port));
        }
    }

    @Override
    public void run() {
        System.out.println(
                String.format("SERVER START ON ADDRESS={%1$s} AND PORT={%2$s}",
                        this.serverSocket.getInetAddress(),
                        this.serverSocket.getLocalPort()
                )
        );
        while (true) {
            try {
                new Thread(new ChatClientHandler(this.serverSocket.accept(), this.clients)).start();
            } catch (Throwable t) {
                System.err.println(
                        String.format("CLIENT NOT HANDLE BECAUSE={%s}",
                                t.getMessage()
                        ));
            }
        }
    }
}
