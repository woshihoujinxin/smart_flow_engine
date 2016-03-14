package com.common;


import com.common.flow.impl.SimpleRouter;
import com.common.flow.impl.StringChecker;
import com.common.flow.IChecker;
import com.common.flow.IRouter;
import com.common.flow.IStep;
import com.common.flow.impl.ConditionRouter;
import com.common.flow.impl.Flow;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 流程构建器
 * Created by houjinxin on 16/3/9.
 */
public class FlowBuilder {

    private Map<String, Class<? extends IStep>> nameClazzMapping;
    private LinkedList<Object> steps;

    public FlowBuilder(Map<String, Class<? extends IStep>> nameClazzMapping, LinkedList<Object> steps) {
        this.nameClazzMapping = nameClazzMapping;
        this.steps = steps;
    }


    public Flow build() {
        Tuple2<Object, IRouter> stepsWithRouter = null;
        try {
            //递归创建由当前step和决定下一步的router组成的二元元组
            stepsWithRouter = build0((LinkedList<Object>) steps.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Flow(stepsWithRouter);
    }

    private Tuple2<Object, IRouter> build0(LinkedList<Object> steps) throws IllegalAccessException, InstantiationException {
        //首元素出队列
        Object stepDescriptor = steps.removeFirst();
        //step可能是一个IStep或者一个表示checker和flow关系的Map对象
        Object step;
        if (stepDescriptor instanceof String) {
            step = nameClazzMapping.get(stepDescriptor).newInstance();
        } else {
            //用于记录check和flow的映射关系
            Map<IChecker, Flow> checkerAndFlows = new HashMap<>();
            //forkMap的key用于标识分支,value代表一个子分支,子分支一定是list类型的
            Map<String, List> forkMap = (Map<String, List>) stepDescriptor;
            //构造checker和flow的映射关系
            Set<Map.Entry<String, List>> entrySet= forkMap.entrySet();
            for (Map.Entry<String, List> entry: entrySet) {
                //用来匹配StepState中的forkFlag和forkMap的key,以决定流程运转中选择哪个分支
                IChecker checker = new StringChecker(entry.getKey());
                //构建分支流程
                Flow forkFlow = new FlowBuilder(nameClazzMapping, new LinkedList<>((Collection<?>) entry.getValue())).build();
                checkerAndFlows.put(checker, forkFlow);
            }
            step = checkerAndFlows;
        }

        //构造router
        IRouter router;
        if (steps.size() != 0) { //有后续步骤
            Object nextStepDescriptor = steps.getFirst();
            //递归构造后续流程
            Tuple2 nextStepsWithRouter = build0(steps);
            if (nextStepDescriptor instanceof String) {
                router = new SimpleRouter(nextStepsWithRouter);
            } else {
                router = new ConditionRouter(nextStepsWithRouter);
            }
        } else { //递归出口
            router = new SimpleRouter(null);
        }
        return new Tuple2(step, router);
    }
}
