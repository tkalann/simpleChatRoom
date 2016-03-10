package org.tkalenko.chat.protocol.entity.impl;

import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.BaseEntity;
import org.tkalenko.chat.protocol.entity.EntityType;

import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class ReceiveEntity extends BaseEntity {
    private EntityType type;
    private Method method;
    private List<Parameter> parameters;

    public ReceiveEntity(final EntityType type, final Method method, final List<Parameter> parameters) {
        if (type == null)
            throw new IllegalArgumentException("type is null");
        if (method == null)
            throw new IllegalArgumentException("method is null");
        if (parameters == null)
            throw new IllegalArgumentException("parameters is null");
        this.type = type;
        this.method = method;
        this.parameters = parameters;
    }

    @Override
    public EntityType getType() {
        return this.type;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public List<Parameter> parameters() {
        return this.parameters;
    }

    @Override
    public void parse(final List<Parameter> parameters) {
        throw new IllegalArgumentException("NOT IMPLEMENT");
    }
}
