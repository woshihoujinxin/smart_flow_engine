package com.cpic.config;

import com.common.rh.IParamsHandler;
import com.common.rh.IResponseHandler;
import com.common.rh.impl.DefaultParamsHandler;
import com.common.rh.impl.DefaultResponseHandler;
import com.cpic.steps.Step1;
import com.cpic.steps.Step2;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于配置步骤与地区和请求参数的映射关系
 * Created by houjinxin on 16/3/11.
 */
public class HandlerMappings {

    public static final Map<String, Map<String, IParamsHandler>> _STEP_CITY_PH_MAPPING = new HashMap<String, Map<String, IParamsHandler>>(){{
        put(Step1.class.getName(), new HashMap<String, IParamsHandler>(){{
            put("default",new DefaultParamsHandler()); //北京
            put("150000",new DefaultParamsHandler());  //呼和浩特
        }});
        put(Step2.class.getName(),  new HashMap<String, IParamsHandler>(){{
            put("default",new DefaultParamsHandler()); //北京
            put("150000",new DefaultParamsHandler());  //呼和浩特
        }});
    }};

    public static final Map<String, Map<String, IResponseHandler>> _STEP_CITY_RH_MAPPING = new HashMap<String, Map<String, IResponseHandler>>(){{
        put(Step1.class.getName(), new HashMap<String, IResponseHandler>(){{
            put("default",new DefaultResponseHandler()); //北京
            put("150000",new DefaultResponseHandler());  //呼和浩特
        }});
        put(Step2.class.getName(),  new HashMap<String, IResponseHandler>(){{
            put("default",new DefaultResponseHandler()); //北京
            put("150000",new DefaultResponseHandler());  //呼和浩特
        }});
    }};

}
