package com.common.flow;

import com.common.StepState;

import java.util.Map;

/**
 * 表示流程中的一个步骤
 * Created by houjinxin on 16/3/9.
 */
public interface IStep {

    /**
     * 该步骤的具体处理情况由实际情况决定
     * @param context 上下文
     * @return 步骤返回值为list,顺序为(成功状态码, 流程是否继续, 分支选择标志, 步骤返回值)
     */
    StepState run(Map<String, Object> context);

}
