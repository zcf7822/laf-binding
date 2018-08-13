package com.jd.laf.binding.converter;


import com.jd.laf.binding.Option;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 转换器
 * Created by hexiaofeng on 16-8-29.
 */
public class Converters {

    // 转换器
    protected static volatile Map<Class<?>, List<Converter>> plugins;
    protected static ConcurrentMap<Class<?>, ConcurrentMap<Class<?>, Option<Converter>>> converters =
            new ConcurrentHashMap<Class<?>, ConcurrentMap<Class<?>, Option<Converter>>>();

    /**
     * 获取转换器
     *
     * @param src    源对象
     * @param target 目标对象
     * @return
     */
    public static Converter getConverter(final Class<?> src, final Class<?> target) {
        //加载插件
        if (plugins == null) {
            synchronized (Converters.class) {
                if (plugins == null) {
                    plugins = new HashMap<Class<?>, List<Converter>>();
                    //加载转换器插件
                    ServiceLoader<Converter> loader = ServiceLoader.load(Converter.class, Converters.class.getClassLoader());
                    for (Converter converter : loader) {
                        for (Class<?> type : converter.types()) {
                            List<Converter> list = plugins.get(type);
                            if (list == null) {
                                list = new ArrayList<Converter>(1);
                                plugins.put(type, list);
                            }
                            list.add(converter);
                        }
                    }

                }
            }
        }

        //判断是否有转化器
        ConcurrentMap<Class<?>, Option<Converter>> options = converters.get(target);
        if (options == null) {
            options = new ConcurrentHashMap<Class<?>, Option<Converter>>();
            ConcurrentMap<Class<?>, Option<Converter>> exists = converters.putIfAbsent(target, options);
            if (exists != null) {
                options = exists;
            }
        }
        Option<Converter> option = options.get(src);
        if (option == null) {
            //没有缓存，则重新计算
            List<Converter> convs = plugins.get(target);
            if (convs != null) {
                for (Converter converter : convs) {
                    if (converter.support(src)) {
                        option = new Option<Converter>(converter);
                        break;
                    }
                }
            }
            //没有找到转换器，则设置一个空选项
            if (option == null) {
                option = new Option<Converter>();
            }
            //缓存
            Option<Converter> exists = options.putIfAbsent(src, option);
            if (exists != null) {
                option = exists;
            }
        }
        return option.get();
    }
}
