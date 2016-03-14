package com.async;

import com.common.service.IThirdPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 报价器
 * Created by houjinxin on 16/3/13.
 */
public class Quoter {

    private Logger logger = LoggerFactory.getLogger(Quoter.class);
    private IThirdPartyService quoteService;
    private Object businessObject;

    public Object doQuote(){
        logger.info("开始报价");
        return this.quoteService.quote(this.businessObject);
    }

    public IThirdPartyService getQuoteService() {
        return quoteService;
    }

    public Quoter setQuoteService(IThirdPartyService quoteService) {
        this.quoteService = quoteService;
        return this;
    }

    public Object getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(Object businessObject) {
        this.businessObject = businessObject;
    }
}
