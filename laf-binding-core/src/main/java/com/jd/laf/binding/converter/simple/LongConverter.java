package com.jd.laf.binding.converter.simple;

import com.jd.laf.binding.converter.Conversion;

/**
 * 长整数转换器
 */
public class LongConverter extends NumberConverter {

    @Override
    public Long execute(final Conversion conversion) {
        if (conversion == null || conversion.source == null) {
            return null;
        } else if (conversion.source instanceof Number) {
            return ((Number) conversion.source).longValue();
        } else if (conversion.source instanceof CharSequence || conversion.source instanceof Character) {
            try {
                return Long.parseLong((conversion.source.toString().trim()));
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    @Override
    public Class<?> targetType() {
        return Long.class;
    }
}
