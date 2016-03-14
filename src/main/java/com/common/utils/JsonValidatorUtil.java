package com.common.utils;


import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * 用于校验一个字符串是否是合法的JSON格式
 * 参考网址: http://outofmemory.cn/code-snippet/7537/Java-to-json-shifou-hefa-to-format-xiaoyan
 */
public class JsonValidatorUtil {

    private CharacterIterator characterIterator;
    private char c;
    private int col;

    public JsonValidatorUtil() {
    }

    /**
     * 验证一个字符串是否是合法的JSON串
     *
     * @param input 要验证的字符串
     * @return true-合法 ，false-非法
     */
    public boolean validate(String input) {
        return validate0(input.trim());
    }

    private boolean validate0(String input) {
        if ("".equals(input)) return true;

        boolean ret = true;
        characterIterator = new StringCharacterIterator(input);
        c = characterIterator.first();
        col = 1;
        if (!value()) {
            ret = error("value", 1);
        } else {
            skipWhiteSpace();
            if (c != CharacterIterator.DONE) {
                ret = error("end", col);
            }
        }

        return ret;
    }

    private boolean value() {
        return literal("true") || literal("false") || literal("null") || string() || number() || object() || array();
    }

    private boolean literal(String text) {
        CharacterIterator ci = new StringCharacterIterator(text);
        char t = ci.first();
        if (c != t) return false;

        int start = col;
        boolean ret = true;
        for (t = ci.next(); t != CharacterIterator.DONE; t = ci.next()) {
            if (t != nextCharacter()) {
                ret = false;
                break;
            }
        }
        nextCharacter();
        if (!ret) error("literal " + text, start);
        return ret;
    }

    private boolean array() {
        return aggregate('[', ']', false);
    }

    private boolean object() {
        return aggregate('{', '}', true);
    }

    private boolean aggregate(char entryCharacter, char exitCharacter, boolean prefix) {
        if (c != entryCharacter) return false;
        nextCharacter();
        skipWhiteSpace();
        if (c == exitCharacter) {
            nextCharacter();
            return true;
        }

        for (; ; ) {
            if (prefix) {
                int start = col;
                if (!string()) return error("string", start);
                skipWhiteSpace();
                if (c != ':') return error("colon", col);
                nextCharacter();
                skipWhiteSpace();
            }
            if (value()) {
                skipWhiteSpace();
                if (c == ',') {
                    nextCharacter();
                } else if (c == exitCharacter) {
                    break;
                } else {
                    return error("comma or " + exitCharacter, col);
                }
            } else {
                return error("value", col);
            }
            skipWhiteSpace();
        }

        nextCharacter();
        return true;
    }

    private boolean number() {
        if (!Character.isDigit(c) && c != '-') return false;
        int start = col;
        if (c == '-') nextCharacter();
        if (c == '0') {
            nextCharacter();
        } else if (Character.isDigit(c)) {
            while (Character.isDigit(c))
                nextCharacter();
        } else {
            return error("number", start);
        }
        if (c == '.') {
            nextCharacter();
            if (Character.isDigit(c)) {
                while (Character.isDigit(c))
                    nextCharacter();
            } else {
                return error("number", start);
            }
        }
        if (c == 'e' || c == 'E') {
            nextCharacter();
            if (c == '+' || c == '-') {
                nextCharacter();
            }
            if (Character.isDigit(c)) {
                while (Character.isDigit(c))
                    nextCharacter();
            } else {
                return error("number", start);
            }
        }
        return true;
    }

    private boolean string() {
        if (c != '"') return false;

        int start = col;
        boolean escaped = false;
        for (nextCharacter(); c != CharacterIterator.DONE; nextCharacter()) {
            if (!escaped && c == '\\') {
                escaped = true;
            } else if (escaped) {
                if (!escape()) {
                    return false;
                }
                escaped = false;
            } else if (c == '"') {
                nextCharacter();
                return true;
            }
        }
        return error("quoted string", start);
    }

    private boolean escape() {
        int start = col - 1;
        if (" \\\"/bfnrtu".indexOf(c) < 0) {
            return error("escape sequence  \\\",\\\\,\\/,\\b,\\f,\\n,\\r,\\t  or  \\uxxxx ", start);
        }
        if (c == 'u') {
            if (!isHex(nextCharacter()) || !isHex(nextCharacter()) || !isHex(nextCharacter())
                    || !isHex(nextCharacter())) {
                return error("unicode escape sequence  \\uxxxx ", start);
            }
        }
        return true;
    }

    private boolean isHex(char d) {
        return "0123456789abcdefABCDEF".indexOf(c) >= 0;
    }

    private char nextCharacter() {
        c = characterIterator.next();
        ++col;
        return c;
    }

    private void skipWhiteSpace() {
        while (Character.isWhitespace(c)) {
            nextCharacter();
        }
    }

    private boolean error(String type, int col) {
//        System.out.printf("type: %s, col: %s%s", type, col, System.getProperty("line.separator"));
        return false;
    }

    public static void main(String[] args) {
        String jsonStr = "[\n" +
                "    \"续保检查\" , {\n" +
                "        \"false\" : [\n" +
                "            \"检查续保通道流程\" , \"M站续保检查\" , {\n" +
                "                \"true\": [\n" +
                "                    \"查询车型是否含税\" , \"根据车辆编码获取品牌\" , \"根据关键字获取品牌\"\n" +
                "                ]\n" +
                "            } , \"检查补充信息\" , \"保存车辆信息\" , {\n" +
                "                \"true\": [\n" +
                "                    \"获取商业险报价\" , {\n" +
                "                        \"true\": [\n" +
                "                            \"获取补充信息\" , {\n" +
                "                                \"true\": [ \"计算商业险报价\" ]\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                ]\n" +
                "            } , \"获取交强险报价\" , {\n" +
                "                \"true\": [ \"获取交强险报价\" ]\n" +
                "            }\n" +
                "        ]\n" +
                "    } , \"检查险种清单\" , \"报价后处理器\"\n" +
                "]\n";
        System.out.println(jsonStr + ":" + new JsonValidatorUtil().validate(jsonStr));
    }
}
