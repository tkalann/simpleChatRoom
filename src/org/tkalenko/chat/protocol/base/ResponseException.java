package org.tkalenko.chat.protocol.base;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class ResponseException extends RuntimeException {
    public ResponseException(final String message) {
        super(message);
    }
}
