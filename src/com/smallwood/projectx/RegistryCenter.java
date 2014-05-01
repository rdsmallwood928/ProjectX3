package com.smallwood.projectx;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bigwood928 on 4/20/14.
 */
public class RegistryCenter {

    private static Map<Class, Object> centerMap = new HashMap<Class, Object>();

    public static <T> void register(Class<T> instance, T object) {
        centerMap.put(instance, object);
    }

    public static <T> T get(Class<T> instance) {
        T implementation = (T) centerMap.get(instance);
        return implementation;
    }
}
