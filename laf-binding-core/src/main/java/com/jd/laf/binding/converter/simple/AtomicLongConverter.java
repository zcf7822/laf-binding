package com.jd.laf.binding.converter.simple;

import com.jd.laf.binding.converter.Conversion;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 长整数转换器
 */
public class AtomicLongConverter extends NumberConverter {

    @Override
    public Object execute(final Conversion conversion) {
        if (conversion == null || conversion.source == null) {
            return null;
        } else if (conversion.source instanceof Number) {
            return new AtomicLong(((Number) conversion.source).longValue());
        } else if (conversion.source instanceof CharSequence || conversion.source instanceof Character) {
            try {
                return new AtomicLong(Long.parseLong((conversion.source.toString().trim())));
            } catch (NumberFormatException e) {
            }
        }
        return null;
    }

    @Override
    public Class<?> targetType() {
        return AtomicLong.class;
    }
}
