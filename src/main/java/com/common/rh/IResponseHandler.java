package com.common.rh;

import com.common.StepState;
import com.common.flow.IStep;

import java.util.Map;

/**
 * 用于不同地区请求参数差异造成的响应结果不同, 提供处理响应的方法
 * Created by houjinxin on 16/3/11.
 */
public interface IResponseHandler {

    /**
     * 处理响应
     * @param context 上下文
     * @param step 当前处理step
     * @param response 响应类型大部分都是String类型,这里使用Object是为了应对可能出现的其他响应类型
     * @return 代表当前步骤状态的StepState
     */
    StepState handleResponse(Map<String, Object> context, IStep step, Object response);

    /**
     * 处理响应
     * @param context 上下文
     * @param step 当前处理step
     * @param response 响应类型大部分都是String类型,这里使用Object是为了应对可能出现的其他响应类型
     * @return 代表当前步骤状态的StepState
     */
    StepState handleResponse(Map<String, Object> context, IStep step, Object response, Object... others);

}
