package com.jd.laf.binding.reflect.java8;

import com.jd.laf.binding.reflect.MethodParameter;
import com.jd.laf.binding.reflect.java6.Java6Parameter;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Java8的参数
 */
public class Java8Parameter extends Java6Parameter {

    public Java8Parameter(Method method, int index, Parameter parameter) {
        super(method, index, parameter.getName(), parameter.getType(), parameter.getParameterizedType(), parameter.getAnnotations());
    }
}