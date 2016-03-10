package org.tkalenko.chat.protocol.entity.impl.message;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.BaseResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class SendMessageResponse extends BaseResponse {
    private boolean send;

    private static final String SEND = "SEND";

    public SendMessageResponse() {
        //no-op
    }

    @Override
    public Method getMethod() {
        return Method.MESSAGE;
    }

    @Override
    public List<Parameter> parameters() {
        return Arrays.asList(new Parameter(SEND, Boolean.toString(this.send)));
    }

    @Override
    public void parse(final List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getName().equals(SEND)) {
                this.send = Boolean.valueOf(parameter.getValue());
                break;
            }
        }
    }

    public boolean isSend() {
        return this.send;
    }

    public void setSend(final boolean send) {
        this.send = send;
    }
}
