package com.common.utils;

import com.common.rh.IParamsHandler;
import com.common.rh.IResponseHandler;
import com.common.StepState;
import com.common.flow.IStep;

import java.util.Map;

/**
 * 上下文初始化时是不包含城市信息的,在流程运转中一定有一步是选择城市的,在那一步(一般是第一步)一定要将城市的cityCode放到上下文中,
 * Created by houjinxin on 16/3/11.
 */
public class StepUtil {

    /**
     * 根据HandlerMappings的配置,获取某一步骤对应城市的请求参数
     * @param context 上下文
     * @param step 当前步骤
     * @param others 其他参数,可选
     * @return
     */
    public static Map<String, String> generateParams(Map<String, Object> context, IStep step, Object... others) {
        String cityCode = (String) context.get("cityCode");
        Map<String, Map<String, IParamsHandler>> stepCityPhMapping = (Map<String, Map<String, IParamsHandler>>) context.get("stepCityPhMapping");
        Map<String, IParamsHandler> cityPhMapping = stepCityPhMapping.get(step.getClass().getName());
        IParamsHandler ph = cityPhMapping.get(cityCode);
        if (others == null) {
            return ph.generateParams(context);
        } else {
            return ph.generateParams(context, others);
        }
    }

    /**
     * 根据HandlerMappings的配置,获取某一步骤对应的处理结果
     * @param context 上下文
     * @param step 当前步骤
     * @param response 代表响应对象
     * @param others 其他参数,可选
     * @return
     */
    public static StepState handleResponse(Map<String, Object> context, IStep step, Object response, Object... others) {
        String cityCode = (String) context.get("cityCode");
        Map<String, Map<String, IResponseHandler>> stepCityRhMapping = (Map<String, Map<String, IResponseHandler>>) context.get("stepCityRhMapping");
        Map<String, IResponseHandler> cityRhMapping = stepCityRhMapping.get(step.getClass().getName());
        IResponseHandler rh = cityRhMapping.get(cityCode);
        if (others == null) {
            return rh.handleResponse(context, step, response);
        } else {
            return rh.handleResponse(context, step, response, others);
        }

    }


}
