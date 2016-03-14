package com.common.flow;

/**
 * 用于判断实际值与期望值相等,目前只支持String类型的对象比较,可以对checker进行扩展以支持更多类型,如果整形,布尔型等等
 * Created by houjinxin on 16/3/10.
 */
public interface IChecker<T> {

    boolean check(T actual);
}
