package com.gky.service;

//创建一个BaseService接口，用泛型解耦
public interface BaseService<T> {
    /**
     * 添加某个对象
     * @param t 待添加的对象
     */
    void add(T t) throws Exception;
}

