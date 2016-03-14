package com.common.flow.impl;

import com.common.StepState;
import com.common.Tuple2;
import com.common.flow.IChecker;
import com.common.flow.IRouter;

import java.util.Map;
import java.util.Set;

import static com.common.Constants._FLOW_CONTINUE_FLAG;

/**
 * 条件路由规则: 若下一步骤是由多个分支组成的map,则根据map的key选择分支子流程
 * Created by houjinxin on 16/3/9.
 */
public class ConditionRouter extends AbstractRouter {

    public ConditionRouter(Tuple2 stepsWithRouter) {
        super(stepsWithRouter);
    }

    @Override
    public StepState selectFork(Map<String, Object> context, StepState preStepState) {
        StepState currentStepState;
        if (_FLOW_CONTINUE_FLAG == preStepState.getContinueFlag() && stepsWithRouter != null) {
            Map<IChecker<String>, Flow> flows = (Map<IChecker<String>, Flow>) stepsWithRouter.getFirst();
            IRouter router = (IRouter) stepsWithRouter.getSecond();
            //寻找与当前StepState的forkFlag相符的分支
            Flow forkFlow = null;
            Set<Map.Entry<IChecker<String>, Flow>> entrySet = flows.entrySet();
            for (Map.Entry<IChecker<String>, Flow> entry : entrySet) {
                IChecker checker = entry.getKey();
                if (checker.check(preStepState.getForkFlag())) {
                    forkFlow = entry.getValue();
                    break;
                }
            }
            //若子分支存在执行流程
            if (forkFlow == null) {
                throw new RuntimeException("步骤名称为[" + preStepState.getStepName() + "]的step, forkFlag参数未指定有效值");
            } else {
                currentStepState = forkFlow.run(context);
            }
            //递归执行后续步骤
            router.selectFork(context, currentStepState);
        } else {
            currentStepState = preStepState;
        }
        return currentStepState;
    }
}
