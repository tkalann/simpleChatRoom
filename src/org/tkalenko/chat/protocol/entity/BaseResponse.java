package org.tkalenko.chat.protocol.entity;

/**
 * Created by tkalenko on 10.03.2016.
 */
public abstract class BaseResponse extends BaseEntity {
	
    @Override
    public EntityType getType() {
        return EntityType.RESPONSE;
    }

}
