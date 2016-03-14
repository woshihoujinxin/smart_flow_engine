package com.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 异步报价启动器
 * Created by houjinxin on 16/3/13.
 */
public class AsyncQuoterStarter implements Runnable {

    private Logger logger = LoggerFactory.getLogger(AsyncQuoterStarter.class);
    private List<AsyncQuoter> asyncQuoters;
    private ExecutorService executorService;

    @Override
    public void run() {
        try {
            List<Future<Object>> futures = executorService.invokeAll(asyncQuoters);
            for (Future<Object> funture: futures) {
                Object result = funture.get();
                if(result != null) {
                    logger.info("websocket推送异步报价结果");
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public List<AsyncQuoter> getAsyncQuoters() {
        return asyncQuoters;
    }

    public AsyncQuoterStarter setAsyncQuoters(List<AsyncQuoter> asyncQuoters) {
        this.asyncQuoters = asyncQuoters;
        return this;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public AsyncQuoterStarter setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }
}
