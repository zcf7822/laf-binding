package com.jd.laf.binding.reflect.array.supplier;

import com.jd.laf.binding.reflect.array.ArrayObject;
import com.jd.laf.binding.reflect.array.PrimitiveIntArray;

/**
 * 整数数组提供者
 */
public class PrimitiveIntArraySupplier implements ArraySupplier {

    @Override
    public ArrayObject create(final int size) {
        return new PrimitiveIntArray(size);
    }

    @Override
    public ArrayObject wrap(final Object array) {
        return new PrimitiveIntArray(array);
    }

    @Override
    public boolean support(final Class<?> clazz) {
        return int.class.equals(clazz);
    }

    @Override
    public int order() {
        return 0;
    }
}
