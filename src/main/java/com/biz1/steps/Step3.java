package com.biz1.steps;


import com.common.StepState;
import com.common.flow.IStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.common.Constants._FLOW_CONTINUE_FLAG;
import static com.common.Constants._STEP_SUCCESS_FLAG;

/**
 * Created by houjinxin on 16/3/9.
 */
public class Step3 implements IStep {

    private Logger logger = LoggerFactory.getLogger(Step1.class);

    @Override
    public StepState run(Map<String, Object> context) {
        logger.info("step3");
        //        throw new RuntimeException(this.getClass().getName()+"出现异常");
        return new StepState(this.getClass().getName(), _STEP_SUCCESS_FLAG, _FLOW_CONTINUE_FLAG, "true", "step3");
    }

}

