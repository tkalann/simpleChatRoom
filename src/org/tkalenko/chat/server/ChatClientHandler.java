package org.tkalenko.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Collection;

import org.tkalenko.chat.protocol.base.InvalidDataException;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Receiver;
import org.tkalenko.chat.protocol.base.ResponseException;
import org.tkalenko.chat.protocol.base.Sender;
import org.tkalenko.chat.protocol.entity.BaseResponse;
import org.tkalenko.chat.protocol.entity.impl.ReceiveEntity;
import org.tkalenko.chat.protocol.entity.impl.message.SendMessageRequest;
import org.tkalenko.chat.protocol.entity.impl.message.SendMessageResponse;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationRequest;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationResponse;

/**
 * Created by tkalenko on 09.03.2016.
 */
public class ChatClientHandler implements Runnable {

    private Socket socket;
    private Collection<ChatClient> clients;
    
    private static final Object mutex = new Object();

    /**
     * Создание обработчика клиента
     *
     * @param socket клиент
     */
    public ChatClientHandler(final Socket socket, final Collection<ChatClient> clients) {
            this.socket = socket;
            this.clients = clients;
    }
    
    private void sendAll(final String message){
    	//TODO: add logic
    }
    
    @Override
    public void run() {
    	try {
			ReceiveEntity entity = Receiver.receive(this.socket);
			if (entity.getMethod() == Method.REGISTRATION) {
				RegistrationRequest request = new RegistrationRequest();
				RegistrationResponse response = new RegistrationResponse();
				
				request.parse(entity);
				synchronized (mutex) {
					ChatClient clientNew = new ChatClient(request.getName(), this.socket);
					if (!this.clients.contains(clientNew)) {
						sendAll(String.format("К нам присоединился пользователь %s", request.getName()));
						this.clients.add(clientNew);
						response.setRegistration(true);
						Sender.send(this.socket, response);
						return;
					} else {
						response.setRegistration(false);
						Sender.send(this.socket, response);
					}
				}
			}
			if (entity.getMethod() == Method.MESSAGE) {
				SendMessageRequest request = new SendMessageRequest();
				SendMessageResponse response = new SendMessageResponse();
				
				request.parse(entity);
				synchronized (mutex) {
					ChatClient client = new ChatClient(request.getUser(), null);
					if (!this.clients.contains(client)) {
						response.setSend(false);
						response.setError(String.format("%s - не зарегестрирован", request.getUser()));
						Sender.send(this.socket, entity);
					} else {
						response.setSend(true);
						Sender.send(this.socket, response);
						sendAll(String.format("%1$s:%2$s", request.getUser(), request.getMessage()));
						return;
					}
				}
			}
			this.socket.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (InvalidDataException e) {
			System.err.println(e.getMessage());
		}
    }
}
