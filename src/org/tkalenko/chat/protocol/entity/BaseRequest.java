package org.tkalenko.chat.protocol.entity;

/**
 * Created by tkalenko on 10.03.2016.
 */
public abstract class BaseRequest extends BaseEntity {

    @Override
    public EntityType getType() {
        return EntityType.REQUEST;
    }
}
