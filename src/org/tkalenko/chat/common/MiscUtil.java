package org.tkalenko.chat.common;

import java.util.Collection;

/**
 * Created by tkalenko on 10.03.2016.
 */
public final class MiscUtil {

    public static boolean isEmpty(final String s){
        return s == null ? true : s.length() == 0;
    }

    public static boolean isEmpty(final Collection collection) {
        return collection == null ? true : collection.isEmpty();
    }

}
