package com.example.democonfig.controller;

import com.example.democonfig.service.ConfigService;
import com.example.democonfig.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;
//    private final Logger logger = LoggerFactory.getLogger(ConfigController.class);



//    @GetMapping("/{key}")
//    public ResponseEntity<String> getConfigValue(@PathVariable String key) {
//        logger.info("Getting config value for key: {}", key);
//        String configValue = configService.getValueByKey(key);
//        if (configValue != null) {
//            return ResponseEntity.ok(configValue);
//        } else {
//            logger.warn("Config value not found for key: {}", key);
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/function/{key}")
    public ResponseEntity<String> callFunction(@PathVariable String key) {
        // (callfunction)
        String configValue = configService.callFunction(key);
        // In kết quả ra console
        System.out.println("Key call:  "+ key);
        System.out.println("Value call " + configValue);

        log.info("Key call json: {}",JsonUtil.writeJsonToString(key));
        log.info("Value call json: {}", JsonUtil.writeJsonToString(configValue));

        return ResponseEntity.ok(configValue);
    }

//    @GetMapping("/procedure/{key}")
//    public ResponseEntity<String> callProcedure(@PathVariable String key) {
//        // In giá trị key ra console
//        System.out.println("Received key: " + key);
//
//        // Gọi phương thức callProcedure từ ConfigService và nhận kết quả
//        String configValue = configService.callProcedure(key);
//
//        // In kết quả ra console
//        System.out.println("Key and Value của key đó " + key + ": " + configValue);
//
//        // Trả về kết quả
//        return ResponseEntity.ok(configValue);
//    }

//    @GetMapping("/stored-procedure/{param}")
//    public ResponseEntity<String> callStoredProc(@PathVariable String param) {
//        // Thực hiện gọi stored procedure từ JdbcTemplate tại đây
//        return ResponseEntity.ok("Calling stored procedure with param: " + param);
//    }
}
