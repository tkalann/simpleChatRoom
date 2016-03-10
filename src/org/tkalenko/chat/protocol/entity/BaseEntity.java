package org.tkalenko.chat.protocol.entity;

import org.tkalenko.chat.common.MiscUtil;
import org.tkalenko.chat.protocol.base.Method;
import org.tkalenko.chat.protocol.base.Parameter;
import org.tkalenko.chat.protocol.entity.impl.ReceiveEntity;

import java.util.List;

/**
 * Created by tkalenko on 10.03.2016.
 */
public abstract class BaseEntity {
	
	private String error;
	
    public abstract EntityType getType();

    public abstract Method getMethod();

    public abstract List<Parameter> parameters();

    public abstract void parse(final List<Parameter> parameters);

    public void parse(final ReceiveEntity receiveEntity) {
        if (receiveEntity == null)
            throw new IllegalArgumentException("receive is null");
        if (getType() != receiveEntity.getType())
            throw new IllegalArgumentException("not this type");
        if (getMethod() != receiveEntity.getMethod())
            throw new IllegalArgumentException("not this method");
        if (!MiscUtil.isEmpty(receiveEntity.parameters()))
            parse(receiveEntity.parameters());
    }

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}
    
    
}
