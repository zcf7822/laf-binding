package com.jd.laf.binding.reflect.array;

/**
 * 单精度浮点数数组
 */
public class PrimitiveFloatArray implements ArrayObject {

    protected float[] array;

    public PrimitiveFloatArray(int size) {
        array = new float[size];
    }

    public PrimitiveFloatArray(Object array) {
        this.array = (float[]) array;
    }

    @Override
    public void set(final int index, final Object value) {
        array[index] = (Float) value;
    }

    @Override
    public Object get(final int index) {
        return array[index];
    }

    @Override
    public int length() {
        return array == null ? 0 : array.length;
    }

    @Override
    public Object getArray() {
        return array;
    }
}
