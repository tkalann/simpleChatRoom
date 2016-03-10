package org.tkalenko.chat.protocol.entity.impl.message;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.BaseRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class SendMessageRequest extends BaseRequest {
    private String user;
    private String message;

    private static final String USER = "USER";
    private static final String MESSAGE = "MESSAGE";

    @Override
    public Method getMethod() {
        return Method.MESSAGE;
    }

    @Override
    public List<Parameter> parameters() {
        if (MiscUtil.isEmpty(this.user))
            throw new IllegalArgumentException("user name is empty");
        if (MiscUtil.isEmpty(this.message))
            throw new IllegalArgumentException("message is empty");
        return Arrays.asList(new Parameter(USER, this.user), new Parameter(MESSAGE, this.message));
    }

    @Override
    public void parse(final List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getName().equals(USER))
                this.user = parameter.getValue();
            if (parameter.getName().equals(MESSAGE))
                this.message = parameter.getValue();
        }
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
