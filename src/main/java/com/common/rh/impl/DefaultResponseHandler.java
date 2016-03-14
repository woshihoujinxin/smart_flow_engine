package com.common.rh.impl;

import com.common.Constants;
import com.common.StepState;
import com.common.rh.IResponseHandler;
import com.common.flow.IStep;

import java.util.Map;

/**
 * 默认响应处理器
 * Created by houjinxin on 16/3/11.
 */
public class DefaultResponseHandler implements IResponseHandler {

    @Override
    public StepState handleResponse(Map<String, Object> context, IStep step, Object response) {
        return new StepState(step.getClass().getName(), Constants._FLOW_CONTINUE_FLAG, Constants._STEP_SUCCESS_FLAG, null, null);
    }

    @Override
    public StepState handleResponse(Map<String, Object> context, IStep step, Object response, Object... others) {
        return null;
    }
}
