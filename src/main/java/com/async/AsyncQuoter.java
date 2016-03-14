package com.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * 异步报价器, 每个保险公司一个线程同时报价
 * Created by houjinxin on 16/3/13.
 */
public class AsyncQuoter implements Callable<Object> {

    private Logger logger = LoggerFactory.getLogger(AsyncQuoter.class);

    private Quoter quoter;

    public Quoter getQuoter() {
        return quoter;
    }

    public AsyncQuoter setQuoter(Quoter quoter) {
        this.quoter = quoter;
        return this;
    }

    @Override
    public Object call() throws Exception {
        logger.info("异步报价器开始报价");
        return quoter.doQuote();
    }
}
