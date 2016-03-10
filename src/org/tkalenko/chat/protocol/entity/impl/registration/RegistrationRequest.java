package org.tkalenko.chat.protocol.entity.impl.registration;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.BaseRequest;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class RegistrationRequest extends BaseRequest {
    private String name;

    private static final String USER = "NAME";

    public RegistrationRequest() {
        //no-op
    }

    @Override
    public Method getMethod() {
        return Method.REGISTRATION;
    }

    @Override
    public List<Parameter> parameters() {
        if (MiscUtil.isEmpty(this.name))
            throw new IllegalArgumentException("name is empty");
        return Arrays.asList(new Parameter(USER, this.name));
    }

    @Override
    public void parse(final List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            if (parameter.getName().equals(USER)){
                this.name = parameter.getValue();
                break;
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
