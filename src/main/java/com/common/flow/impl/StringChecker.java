package com.common.flow.impl;


/**
 * 检查期望字符串和实际字符串是否相同,用于选择流程的子分支.
 * Created by houjinxin on 16/3/10.
 */
public class StringChecker extends AbstractChecker<String> {

    public StringChecker(String except) {
        super(except);
    }

    @Override
    public boolean check(String actual) {
        return this.except.equals(actual);
    }
}
