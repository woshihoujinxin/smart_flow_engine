package com.mock;

import com.async.AsyncQuoter;
import com.async.AsyncQuoterStarter;
import com.async.Quoter;
import com.biz1.service.Biz1Service;
import com.biz2.service.Biz2Service;
import com.biz3.service.Biz3Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by houjinxin on 16/3/9.
 */
public class Client {

    public static void main(String[] args) {

//                new Biz3Service().quote(new Object());
//                new Biz2Service().quote(new Object());
//                new Biz1Service().quote(new Object());
        Quoter[] quoters = {
                new Quoter().setQuoteService(new Biz3Service()),
                new Quoter().setQuoteService(new Biz2Service()),
                new Quoter().setQuoteService(new Biz1Service())
        };
        List<AsyncQuoter> asyncQuoters = new ArrayList<>();
        for (Quoter quoter : quoters) {
            asyncQuoters.add(new AsyncQuoter().setQuoter(quoter));
        }
        ExecutorService execSvc = Executors.newCachedThreadPool();
        execSvc.submit(new AsyncQuoterStarter().setExecutorService(execSvc).setAsyncQuoters(asyncQuoters));


//        execSvc.shutdown();
    }
}
