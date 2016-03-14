package com.common;

/**
 * step状态描述
 * Created by houjinxin on 16/3/9.
 */
public class StepState {

    private String stepName;
    //步骤执行是否成功 1--成功 0--失败 以及其他
    private int statusFlag;
    //流程是否继续标志 1--继续 0--终止
    private int continueFlag;
    //分支标志
    private Object forkFlag;
    private String errorMsg;
    //步骤返回信息
    private Object stepInfo;

    public StepState(String stepName, int statusFlag, int continueFlag, Object forkFlag, String errorMsg) {
        this(stepName, statusFlag, continueFlag, forkFlag, errorMsg, null);
    }

    //用于需要返回某一步骤特定信息的情况
    public StepState(String stepName, int statusFlag, int continueFlag, Object forkFlag, String errorMsg, Object stepInfo) {
        this.stepName = stepName;
        this.statusFlag = statusFlag;
        this.continueFlag = continueFlag;
        this.forkFlag = forkFlag;
        this.errorMsg = errorMsg;
        this.stepInfo = stepInfo;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public int getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(int statusFlag) {
        this.statusFlag = statusFlag;
    }

    public int getContinueFlag() {
        return continueFlag;
    }

    public void setContinueFlag(int continueFlag) {
        this.continueFlag = continueFlag;
    }

    public Object getForkFlag() {
        return forkFlag;
    }

    public void setForkFlag(Object forkFlag) {
        this.forkFlag = forkFlag;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getStepInfo() {
        return stepInfo;
    }

    public void setStepInfo(Object stepInfo) {
        this.stepInfo = stepInfo;
    }
}
