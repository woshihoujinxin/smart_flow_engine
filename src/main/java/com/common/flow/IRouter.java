package com.common.flow;

import com.common.StepState;

import java.util.Map;

/**
 * 路由规则:根据前一个步骤的结果来选择下一个步骤,或者是一个步骤或者是一个分支
 * Created by houjinxin on 16/3/9.
 */
public interface IRouter {

    StepState selectFork(Map<String, Object> context, StepState preStepState);
}
