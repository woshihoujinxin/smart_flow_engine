package com.common.utils;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 流程构建工具类
 * Created by houjinxin on 16/3/11.
 */
public class FlowBuilderUtil {

    private static final ObjectMapper _MAPPER = new ObjectMapper();

    /**
     * 对于只有单个流程的不需要名称访问
     *
     * @param flowFilePath 流程配置文件路径
     * @return 流程所有步骤构成的链表
     */
    public static LinkedList<Object> extractSteps(String flowFilePath) {
        return extractSteps(flowFilePath, null);
    }

    /**
     * 多个流程配置中获取指定名称的流程
     *
     * @param flowFilePath 流程配置文件路径
     * @param flowName     流程名称
     * @return 流程所有步骤构成的链表
     */
    public static LinkedList<Object> extractSteps(String flowFilePath, String flowName) {
        List<Map<String, Object>> stepList = null;
        try {
            String flowStr = readFlowFile(flowFilePath);
            String standardJson = flowStr;
            //若流程配置是标准Json格式则不需要转换
            if (!new JsonValidatorUtil().validate(flowStr)) {
                standardJson = convertDSLToStandardJson(flowStr);
            }
            JsonNode flowNode = _MAPPER.readTree(standardJson);
            if (flowName != null && !flowName.equals("")) {
                flowNode = flowNode.findValue(flowName);
            }
            stepList = _MAPPER.readValue(flowNode, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LinkedList<Object>(stepList);
    }

    private static String convertDSLToStandardJson(String nonStandardJson) {
        return nonStandardJson.replaceAll("->", ",")
                .replaceAll("\\s", "")
                .replaceAll("\\{", "<")
                .replaceAll("}", ">")
                .replaceAll("\\[", "{")
                .replaceAll("]", "}")
                .replaceAll("<", "[")
                .replaceAll(">", "]")
                .replaceAll("([^\\[\\,\\{\\}\\:\\[\\]\\s]*)", "\"$1\"").replaceAll("\"\"", "");
    }

    private static String readFlowFile(String flowFilePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<String> lines = FileUtils.readLines(new File(flowFilePath));
        for (String everyline : lines) {
            sb.append(everyline);
        }
        return sb.toString();
    }

}