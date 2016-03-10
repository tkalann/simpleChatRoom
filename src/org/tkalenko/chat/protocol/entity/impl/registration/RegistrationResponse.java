package org.tkalenko.chat.protocol.entity.impl.registration;

import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.BaseResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class RegistrationResponse extends BaseResponse {
    private boolean registration;

    private static final String REGISTRATION = "REGISTRATION";

    public RegistrationResponse() {
        //no-op
    }

    @Override
    public Method getMethod() {
        return Method.REGISTRATION;
    }

    @Override
    public List<Parameter> parameters() {
        return Arrays.asList(new Parameter(REGISTRATION, Boolean.toString(this.registration)));
    }

    @Override
    public void parse(final List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getName().equals(REGISTRATION)) {
                this.registration = Boolean.valueOf(parameter.getValue());
                break;
            }
        }
    }

    public boolean isRegistration() {
        return this.registration;
    }

    public void setRegistration(final boolean registration) {
        this.registration = registration;
    }
}
