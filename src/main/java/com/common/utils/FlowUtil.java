package com.common.utils;


import com.common.flow.IFlow;

import java.util.Map;

/**
 * 流程操作用到的一些通用方法
 * Created by houjinxin on 16/3/11.
 */
public class FlowUtil {

    public static IFlow getFlow(Map<String, Object> context) {
        String cityCode = (String) context.get("cityCode");
        if (cityCode == null) {
            cityCode = "default";
        }
        Map<String, IFlow> cityFlowMapping = (Map<String, IFlow>) context.get("cityFlowMapping");
        return cityFlowMapping.get(cityCode);
    }
}
