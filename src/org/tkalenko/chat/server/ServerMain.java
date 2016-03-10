package org.tkalenko.chat.server;

import org.tkalenko.chat.common.Util;
import org.tkalenko.chat.protocol.base.Receiver;
import org.tkalenko.chat.protocol.base.Sender;
import org.tkalenko.chat.protocol.base.InvalidDataException;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.entity.impl.ReceiveEntity;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationRequest;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationResponse;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tkalenko on 09.03.2016.
 */
public class ServerMain {

    // TODO: 10.03.2016 ИЗМЕНИТЬ ЛОГИКУ
    
    public static void main(String[] args) throws IOException, InvalidDataException {
    	ChatServer server = new ChatServer(Util.DEFAULT_SERVER_PORT);
    	Thread thread = new Thread(server);
    	thread.start();
    }
}
