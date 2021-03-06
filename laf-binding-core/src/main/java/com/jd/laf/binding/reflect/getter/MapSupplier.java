package com.jd.laf.binding.reflect.getter;

import com.jd.laf.binding.reflect.PropertySupplier;

import java.util.Map;

/**
 * MAP属性获取器
 */
public class MapSupplier implements PropertySupplier {

    @Override
    public Object get(final Object target, final String name) {
        return ((Map<?, ?>) target).get(name);
    }

    @Override
    public boolean support(final Class<?> clazz) {
        return clazz != null && Map.class.isAssignableFrom(clazz);
    }
}
