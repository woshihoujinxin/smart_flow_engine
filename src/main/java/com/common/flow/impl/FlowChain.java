package com.common.flow.impl;

import com.common.StepState;
import com.common.flow.IFlow;

import java.util.LinkedList;
import java.util.Map;

import static com.common.Constants._FLOW_CONTINUE_FLAG;
import static com.common.Constants._FLOW_FINISHED_FLAG;
import static com.common.Constants._STEP_SUCCESS_FLAG;

/**
 * 流程链定义: 多个流程链可以组成一个更复杂的流程
 * Created by houjinxin on 16/3/10.
 */
public class FlowChain implements IFlow {

    private LinkedList<Flow> flows;

    public FlowChain(LinkedList<Flow> flows) {
        this.flows = flows;
    }

    @Override
    public StepState run(Map<String, Object> context) {
        //preStepState代表上一个流程链的结果,这里设置初始值为可以继续执行
        StepState preStepState = new StepState("default", _FLOW_CONTINUE_FLAG, _STEP_SUCCESS_FLAG, null, null);
        for (Flow flow : flows) {
            preStepState = executeFlowChain(preStepState, flow, context);
            if (preStepState.getContinueFlag() == _FLOW_FINISHED_FLAG) {
                break;
            }
        }
        return preStepState;
    }

    /**
     * 根据前一个流程的StepState,确定当前流程链是否继续
     *
     * @param preStepState 前一个流程执行的结束状态
     * @param flow         当前流程
     * @param context      上下文
     * @return
     */
    private StepState executeFlowChain(StepState preStepState, Flow flow, Map<String, Object> context) {
        StepState stepState;
        if (preStepState.getContinueFlag() == _FLOW_CONTINUE_FLAG) {
            stepState = flow.run(context);
        } else {
            stepState = preStepState;
        }
        return stepState;
    }
}
