package com.example.democonfig.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

@Slf4j
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getStringFromJson(String json, String key) {
        try {
            TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {};
            HashMap<String, Object> o = mapper.readValue(json, typeRef);
            return o.getOrDefault(key, "").toString();
        } catch (Exception e) {
            log.error("Error getting string from JSON: input {}, key {}", json, key, e);
            return "";
        }
    }

    public static String writeJsonToString(Object json) {
        try {
            return mapper.writeValueAsString(json);
        } catch (Exception e) {
            log.error("Error writing JSON to string: input {}", json, e);
            return "";
        }
    }
}
