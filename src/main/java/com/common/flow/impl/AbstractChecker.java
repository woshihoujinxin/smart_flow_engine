package com.common.flow.impl;

import com.common.flow.IChecker;

public abstract class AbstractChecker<T> implements IChecker<T> {

    protected T except;

    public AbstractChecker(T except) {
        this.except = except;
    }

}
