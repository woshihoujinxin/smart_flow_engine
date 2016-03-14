package com.common.flow.impl;

import com.common.Tuple2;
import com.common.flow.IRouter;

/**
 * 抽象Router
 * Created by houjinxin on 16/3/9.
 */
public abstract class AbstractRouter implements IRouter {

    protected Tuple2 stepsWithRouter;

    public AbstractRouter(Tuple2 stepsWithRouter){
        this.stepsWithRouter = stepsWithRouter;
    }

}
