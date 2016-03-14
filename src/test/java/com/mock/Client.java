package com.mock;

import com.async.AsyncQuoter;
import com.async.AsyncQuoterStarter;
import com.async.Quoter;
import com.cpic.service.CpicService;
import com.picc.service.PiccService;
import com.pingan.service.PinganService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by houjinxin on 16/3/9.
 */
public class Client {

    public static void main(String[] args) {

//                new PinganService().quote(new Object());
//                new PiccService().quote(new Object());
//                new CpicService().quote(new Object());
        Quoter[] quoters = {
                new Quoter().setQuoteService(new PinganService()),
                new Quoter().setQuoteService(new PiccService()),
                new Quoter().setQuoteService(new CpicService())
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
