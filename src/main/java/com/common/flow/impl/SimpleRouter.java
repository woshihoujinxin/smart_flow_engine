package com.common.flow.impl;

import com.common.Tuple2;
import com.common.StepState;
import com.common.flow.IRouter;
import com.common.flow.IStep;

import java.util.Map;

import static com.common.Constants._FLOW_CONTINUE_FLAG;

/**
 * 简单路由规则:
 * 如果"继续",就直接调用下一个step
 * Created by houjinxin on 16/3/9.
 */
public class SimpleRouter extends AbstractRouter {

    public SimpleRouter(Tuple2 stepsWithRouter) {
        super(stepsWithRouter);
    }

    @Override
    public StepState selectFork(Map<String, Object> context, StepState preStepState) {
        StepState currentStepState ;
        if (_FLOW_CONTINUE_FLAG == preStepState.getContinueFlag() && stepsWithRouter != null) {
            IStep step = (IStep) stepsWithRouter.getFirst();
            IRouter router = (IRouter) stepsWithRouter.getSecond();
            currentStepState = step.run(context);
            //递归执行后续步骤
            router.selectFork(context, currentStepState);
        } else {
            currentStepState = preStepState;
        }
        return currentStepState;
    }
}
