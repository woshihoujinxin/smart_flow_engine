package com.common.rh.impl;

import com.common.rh.IParamsHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的参数生成器
 * Created by houjinxin on 16/3/11.
 */
public class DefaultParamsHandler implements IParamsHandler {
    @Override
    public Map<String, String> generateParams(Map<String, Object> context) {
        return new HashMap<>();
    }

    @Override
    public Map<String, String> generateParams(Map<String, Object> context, Object... others) {
        return null;
    }
}
