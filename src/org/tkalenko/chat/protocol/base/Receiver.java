package org.tkalenko.chat.protocol.base;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.entity.EntityType;
import org.tkalenko.chat.protocol.entity.impl.ReceiveEntity;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public final class Receiver extends BaseHandler {
	
	private Receiver(){
		//no-op
	}
	
    /**
     * Получение ответа из сокета
     *
     * @param socket сокет
     * @return ответ от второй стороны
     * @throws IOException              если проблемы с сокетом
     * @throws IllegalArgumentException пустой сокет
     * @throws InvalidDataException     если не верные данные пришли
     * @throws ResponseException        если от второй стороны пришла ошибка
     */
    public static ReceiveEntity receive(final Socket socket) throws IOException, IllegalArgumentException, InvalidDataException, ResponseException {
        if (socket == null)
            throw new IllegalArgumentException("socket is null");
        DataInputStream stream = new DataInputStream(socket.getInputStream());
        EntityType type = null;
        Method method = null;
        List<Parameter> parameters = new ArrayList<Parameter>();

        String s = null;
        Parameter parameter = null;
        //проверка начала
        s = stream.readUTF();
        if (MiscUtil.isEmpty(s) || !s.equals(START))
            throw new InvalidDataException("not by protocol");
        //проверка типа
        s = stream.readUTF();
        if (MiscUtil.isEmpty(s))
            throw new InvalidDataException("not by protocol");
        type = EntityType.valueOf(s);
        //проверка метода
        s = stream.readUTF();
        parameter = new Parameter(s);
        if (!parameter.getName().equals(METHOD))
            throw new InvalidDataException("not by protocol");
        method = Method.valueOf(parameter.getValue());
        //получение параметров
        while (true) {
            s = stream.readUTF();
            if (MiscUtil.isEmpty(s))
                throw new InvalidDataException("not by protocol");
            if (s.equals(END))
                break;
            parameter = new Parameter(s);
            if (parameter.equals(ERROR))
                throw new ResponseException(parameter.getValue());
            parameters.add(parameter);
        }
        return new ReceiveEntity(type, method, parameters);
    }
}
