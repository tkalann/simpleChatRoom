package org.tkalenko.chat.protocol.base;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.entity.BaseEntity;
import org.tkalenko.chat.protocol.entity.EntityType;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public final class Sender extends BaseHandler {
	
	private Sender(){
		//no-op
	}
	
    /**
     * Послать данные по сокету. Это может быть как запрос так и ответ
     *
     * @param socket сокет
     * @param entity запрос/ответ
     * @throws IOException              если есть проблемы с сокетом
     * @throws IllegalArgumentException если входные данные не валидны
     */
    public static void send(final Socket socket, final BaseEntity entity) throws IOException, IllegalArgumentException {
        if (socket == null)
            throw new IllegalArgumentException("socket is null");
        if (entity == null)
            throw new IllegalArgumentException("entity is null");

        EntityType type = entity.getType();
        Method method = entity.getMethod();
        List<Parameter> parameters = entity.parameters();

        if (type == null)
            throw new IllegalArgumentException("type is null");
        if (method == null)
            throw new IllegalArgumentException("method is null");
        if (parameters == null)
            throw new IllegalArgumentException("parameters is null");
        for (Parameter parameter : parameters) {
            if (parameter == null)
                throw new IllegalArgumentException("null in collection of parameters");
        }

        DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
        write(stream, START);
        write(stream, type.name());
        write(stream, METHOD, method.name());
        if (!MiscUtil.isEmpty(entity.getError())) {
			write(stream, ERROR, entity.getError());
		} else {
			for (Parameter parameter : parameters) {
	            write(stream, parameter);
	        }
		}
        write(stream, END);
    }

    /**
     * Запись сообщения в выходной поток
     *
     * @param stream  выходной поток
     * @param message сообщение
     * @throws IOException если проблема с потоком
     */
    private static void write(final DataOutputStream stream, final String message) throws IOException {
        stream.writeUTF(message);
        stream.flush();
    }

    /**
     * Составление сообщение и его отправка в выходной поток <br/>
     * сообщение типа: name={value}
     *
     * @param stream выходной поток
     * @param name   название параметра
     * @param value  значение параметра
     * @throws IOException если проблема с потоком
     */
    private static void write(final DataOutputStream stream, final String name, String value) throws IOException {
        write(stream, String.format("%1$s={%2$s}", name, value));
    }

    /**
     * Отправка параметра в выходной поток
     *
     * @param stream    выходной поток
     * @param parameter параметер
     * @throws IOException
     */
    private static void write(final DataOutputStream stream, final Parameter parameter) throws IOException {
        write(stream, parameter.getName(), parameter.getValue());
    }
}
