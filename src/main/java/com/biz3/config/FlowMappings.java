package com.biz3.config;

import com.common.flow.IFlow;

import java.util.HashMap;
import java.util.Map;

/**
 * 城市与流程的映射关系
 * Created by houjinxin on 16/3/11.
 */
public class FlowMappings {
    public static final Map<String, IFlow> _CITY_FLOW_MAPPING = new HashMap<String, IFlow>() {{
        put("default", Flows._FLOW_CHAIN_TYPE1);
    }};
}
