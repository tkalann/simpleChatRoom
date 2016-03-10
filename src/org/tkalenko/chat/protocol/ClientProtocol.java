package org.tkalenko.chat.protocol;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.base.InvalidDataException;
import org.tkalenko.chat.protocol.base.Receiver;
import org.tkalenko.chat.protocol.base.ResponseException;
import org.tkalenko.chat.protocol.base.Sender;
import org.tkalenko.chat.protocol.entity.impl.message.SendMessageRequest;
import org.tkalenko.chat.protocol.entity.impl.message.SendMessageResponse;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationRequest;
import org.tkalenko.chat.protocol.entity.impl.registration.RegistrationResponse;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by tkalenko on 10.03.2016.
 */
public final class ClientProtocol {

    /**
     * Запрос на регистрацию пользователя на сервере
     *
     * @param socket   сокет
     * @param userName имя пользователя
     * @return true- зарегестрирован, false-не зарегестрировался
     * @throws IllegalArgumentException если socket или userName пусты
     */
    public static boolean registration(final Socket socket, final String userName) throws IllegalArgumentException {
        if (socket == null)
            throw new IllegalArgumentException("socket is null");
        if (MiscUtil.isEmpty(userName))
            throw new IllegalArgumentException("userName is empty");
        try {
            RegistrationRequest request = new RegistrationRequest();
            request.setName(userName);
            Sender.send(socket, request);
            RegistrationResponse response = new RegistrationResponse();
            response.parse(Receiver.receive(socket));
            return response.isRegistration();
        } catch (Throwable t) {
        }
        return false;
    }

    /**
     * Отправка сообщения от пользователя на сервер
     *
     * @param socket   сокет
     * @param userName имя пользователя
     * @param message  сообщение от пользователя
     * @throws ResponseException если проблема с отправкой сообщения
     */
    public static void sendMessage(final Socket socket, final String userName, final String message) throws ResponseException {
        if (socket == null)
            throw new IllegalArgumentException("socket is null");
        if (MiscUtil.isEmpty(userName))
            throw new IllegalArgumentException("userName is empty");
        if (MiscUtil.isEmpty(message))
            throw new IllegalArgumentException("message is empty");
        try {
            SendMessageRequest request = new SendMessageRequest();
            request.setUser(userName);
            request.setMessage(message);
            Sender.send(socket, request);
            SendMessageResponse response = new SendMessageResponse();
            response.parse(Receiver.receive(socket));
            if (!response.isSend()) {
                throw new ResponseException("message don't send");
            }
        } catch (IOException e) {
            throw new ResponseException("message can't send");
        } catch (InvalidDataException e) {
            throw new ResponseException("message can't send");
        }
        return;
    }

}
