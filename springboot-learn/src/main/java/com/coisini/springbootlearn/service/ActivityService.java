package com.coisini.springbootlearn.service;


import com.coisini.springbootlearn.model.Activity;

/**
 * @Description Activity 接口
 * @author coisini
 * @date Aug 20, 2021
 * @Version 1.0
 */
public interface ActivityService {

    /**
     * getByName
     * @param name
     * @return
     */
    Activity getByName(String name);

}
