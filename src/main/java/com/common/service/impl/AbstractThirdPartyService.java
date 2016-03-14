package com.common.service.impl;

import com.common.Constants;
import com.common.StepState;
import com.common.flow.IFlow;
import com.common.service.IThirdPartyService;
import com.common.utils.FlowUtil;
import org.slf4j.Logger;

import java.util.Map;

/**
 * 模板方法实现
 * Created by houjinxin on 16/3/13.
 */
public abstract class AbstractThirdPartyService implements IThirdPartyService {

    /**
     * 根据业务需要来创建不同的上下文
     * @return
     */
    abstract protected Map<String, Object> createContext();

    /**
     * 用流程执行的结果来修改原始业务对象
     * @param obj 业务对象
     * @return 修改后的业务对象
     */
    @Override
    public final Object quote(Object obj) {
        Map<String, Object> context = createContext();
        IFlow flow = FlowUtil.getFlow(context);
        service(obj, context, flow);
        return obj;
    }

    /**
     * 各类流程启动,将最终的处理结果赋值给业务对象
     * @param obj 业务对象
     * @param context 上下文
     * @param flow 流程
     */
    private void service(Object obj, Map<String, Object> context, IFlow flow) {
        try {
            Logger logger = getLogger();
            long beginTime = System.currentTimeMillis();
            StepState stepState = flow.run(context);
            long endTime = System.currentTimeMillis();
            logger.info("调用接口所需时间：" + ((endTime - beginTime) / 1000));
            if(stepState.getStatusFlag() == Constants._STEP_SUCCESS_FLAG){
                logger.info(this.getClass().getName()+"报价成功");
                updateBusinessObject(obj);
            } else {

            }
            //针对不同场景定义一些业务异常,在适当时候抛出业务异常
        } catch (Exception e) {
            //if(e instanceOf BussinessException)
                throw e;
            //非特定业务异常的其他情况转为业务异常抛出
            //throw new BussinessException("")
        }
    }

    protected Object updateBusinessObject(Object obj){
        return new Object();
    }

    abstract protected Logger getLogger();

}
