package com.common.flow.impl;

import com.common.StepState;
import com.common.Tuple2;
import com.common.flow.IFlow;
import com.common.flow.IRouter;
import com.common.flow.IStep;

import java.util.Map;


public class Flow implements IFlow {

    //元组的第一个元素可能是IStep或IFlow接口的实现,故用Object类型
    private Tuple2<Object, IRouter> stepsWithRouter;

    public Flow(Tuple2<Object, IRouter> stepsWithRouter){
        this.stepsWithRouter = stepsWithRouter;
    }

    @Override
    public StepState run(Map<String, Object> context) {
        IStep step = (IStep) stepsWithRouter.getFirst();
        IRouter router = stepsWithRouter.getSecond();
        StepState stepState = step.run(context);
        return router.selectFork(context, stepState);
    }
}
