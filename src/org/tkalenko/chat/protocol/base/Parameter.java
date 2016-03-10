package org.tkalenko.chat.protocol.base;

import org.tkalenko.chat.common.MiscUtil;

/**
 * Created by tkalenko on 10.03.2016.
 */
public class Parameter {
    private String name;
    private String value;

    public Parameter(final String name, final String value) {
        if (MiscUtil.isEmpty(name))
            throw new IllegalArgumentException("empty name");
        if (MiscUtil.isEmpty(value))
            throw new IllegalArgumentException("empty value");
        this.name = name;
        this.value = value;
    }

    /**
     * Создание экземпляра из входной строки
     * @param s входная строка
     */
    public Parameter(final String s) {
        if (MiscUtil.isEmpty(s))
            throw new IllegalArgumentException("empty s");
        StringBuilder builder = new StringBuilder(s);
        for (int i = 0, startValue = -1; i < builder.length(); i++) {
            char c = builder.charAt(i);
            if (c == '=') {
                this.name = builder.substring(0, i);
                continue;
            }
            if (c == '{') {
                startValue = i + 1;
                continue;
            }
            if (c == '}') {
                this.value = builder.substring(startValue, i);
                break;
            }
        }
        if (MiscUtil.isEmpty(this.name) || MiscUtil.isEmpty(this.value))
            throw new IllegalArgumentException("invalid s");
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
