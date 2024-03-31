package com.example.democonfig.service;

import com.example.democonfig.entity.Config;
import com.example.democonfig.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Collections;



@Service
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getValueByKey(String key) {
        Config configEntity = configRepository.findByKey(key);
        if (configEntity != null) {
            return configEntity.getValue();
        } else {
            return null; // or throw exception if desired
        }
    }

    String defaultValue = "Default Value";
    public String callFunction(String configKey) {
        try {
            String sql = "SELECT get_config_value(?)";
            return jdbcTemplate.queryForObject(sql, new Object[]{configKey}, String.class);
        } catch (DataAccessException e) {
            System.err.println("Error accessing data: " + e.getMessage());
            // Có thể throw hoặc return một giá trị mặc định tùy theo logic ứng dụng của bạn
            throw new RuntimeException("Error accessing data", e);
        }


    }

//    public String callProcedure(String configKey) {
//        String procedureName = "get_config_value_proc";
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(procedureName);
//        SqlParameter sqlParameter = new SqlParameter("config_key", Types.VARCHAR);
//        jdbcCall.addDeclaredParameter(sqlParameter);
//        jdbcCall.addDeclaredParameter(new SqlParameter("config_value", Types.VARCHAR));
//        jdbcCall.withReturnValue();
//        Object[] params = { configKey };
//        String result = jdbcCall.executeFunction(String.class, params);
//        return result;
//    }


}


