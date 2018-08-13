package com.jd.laf.binding.converter;

/**
 * 布尔转换器
 */
public class BooleanConverter extends NumberConverter {

    @Override
    public Object convert(final Conversion conversion) {
        if (conversion == null || conversion.source == null) {
            return null;
        } else if (conversion.source instanceof Number) {
            return ((Number) conversion.source).intValue() != 0 ? Boolean.TRUE : Boolean.FALSE;
        } else if (conversion.source instanceof Character) {
            return ((Character) conversion.source) != '0' ? Boolean.TRUE : Boolean.FALSE;
        } else if (conversion.source instanceof CharSequence) {
            String value = conversion.source.toString();
            if ("true".equalsIgnoreCase(value)) {
                return true;
            } else if ("false".equalsIgnoreCase(value)) {
                return false;
            }
            try {
                return Long.parseLong(value) != 0;
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Class<?>[] types() {
        return new Class[]{boolean.class, Boolean.class};
    }
}