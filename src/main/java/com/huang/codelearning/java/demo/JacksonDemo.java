package com.huang.codelearning.java.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huang.codelearning.java.constants.Constants;
import com.huang.codelearning.java.pojo.WebSitePo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * demo
 */
public class JacksonDemo {
    private static final Logger logger = LoggerFactory.getLogger(JacksonDemo.class);
   

    @Test
    public void test() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //jackson解析字符串,解析json数组
        JsonNode jsonNode = objectMapper.readTree(Constants.jsonStr);
        JsonNode functionNode = jsonNode.get("functions");
        if (functionNode.isArray()){
            for (JsonNode node : functionNode) {
                System.out.println(node);
            }
        }

        //json转map
        Map map = objectMapper.readValue(Constants.jsonStr, Map.class);

        //map转json
        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);

        WebSitePo webSitePo = WebSitePo.builder().title("hello world")
                .keywords("start programming")
                .functions("practice java")
                .url("www.program.com")
                .build();

        //对象转json
        String webSite = objectMapper.writeValueAsString(webSitePo);
        System.out.println(webSite);

        //json转兑对象
        WebSitePo webSitePo1 = objectMapper.readValue(webSite, WebSitePo.class);
        System.out.println(webSitePo1);

    }


}

