package org.tkalenko.chat.client;

import org.tkalenko.chat.protocol.ClientProtocol;

import java.io.*;
import java.net.Socket;

/**
 * Created by tkalenko on 09.03.2016.
 */
public class ClientMain {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(org.tkalenko.chat.common.Util.DEFAULT_SERVER_ADDRESS, org.tkalenko.chat.common.Util.DEFAULT_SERVER_PORT);
        System.out.println(ClientProtocol.registration(socket, "kirill"));
        socket.close();
    }
}
