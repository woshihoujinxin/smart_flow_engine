package com.biz2.config;

import com.common.FlowBuilder;
import com.common.flow.IStep;
import com.common.flow.impl.Flow;
import com.common.flow.impl.FlowChain;
import com.common.utils.FlowBuilderUtil;
import com.biz2.steps.Step1;
import com.biz2.steps.Step2;
import com.biz2.steps.Step3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 流程与步骤配置
 * 可以配置子流程,并用子流程拼装成流程链
 * Created by houjinxin on 16/3/9.
 */
public class Flows {

    private static final String _FLOW_FILE_PATH = "/Users/houjinxin/Documents/baoping/parser/src/main/resources/flows2";

    public static final Map<String, Class<? extends IStep>> _NAME_STEP_CLAZZ_MAPPINGS = new HashMap<String, Class<? extends IStep>>() {{
        put("step1", Step1.class);
        put("step2", Step2.class);
        put("step3", Step3.class);
        put("步骤1", Step1.class);
        put("步骤2", Step2.class);
        put("步骤3", Step3.class);
    }};

    public static final LinkedList<Object> _FLOW_TYPE1_STEPS = FlowBuilderUtil.extractSteps(_FLOW_FILE_PATH, "flow_type1");

    public static final Flow _FLOW_TYPE1 = new FlowBuilder(_NAME_STEP_CLAZZ_MAPPINGS, _FLOW_TYPE1_STEPS).build();

    public static final LinkedList<Object> _FLOW_TYPE2_STEPS = FlowBuilderUtil.extractSteps(_FLOW_FILE_PATH, "flow_type2");

    public static final Flow _FLOW_TYPE2 = new FlowBuilder(_NAME_STEP_CLAZZ_MAPPINGS, _FLOW_TYPE2_STEPS).build();

    public static final FlowChain _FLOW_CHAIN_TYPE1 = new FlowChain(new LinkedList<Flow>(){{
        add(_FLOW_TYPE1);
        add(_FLOW_TYPE2);
    }});

}
