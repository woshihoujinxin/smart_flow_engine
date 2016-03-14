package com.pingan.service;

import com.pingan.config.FlowMappings;
import com.pingan.config.HandlerMappings;
import com.common.service.impl.AbstractThirdPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houjinxin on 16/3/13.
 */
public class PinganService extends AbstractThirdPartyService {

    private static Logger logger = LoggerFactory.getLogger(PinganService.class);

    @Override
    protected Map<String, Object> createContext() {
        Map<String, Object> context = new HashMap<String, Object>(){{
            put("cityFlowMapping", FlowMappings._CITY_FLOW_MAPPING);
            put("stepCityPhMapping", HandlerMappings._STEP_CITY_PH_MAPPING);
            put("stepCityRhMapping", HandlerMappings._STEP_CITY_RH_MAPPING);
        }};
        return context;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }
}
