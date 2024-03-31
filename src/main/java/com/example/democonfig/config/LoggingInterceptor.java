package com.example.democonfig.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Request URL: {}", request.getRequestURL());
        logger.info("Request Method: {}", request.getMethod());
        if (request.getContentType() != null && request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            logger.info("Request Body: {}", new String(request.getInputStream().readAllBytes()));
        }
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (logger.isInfoEnabled()) {
            logger.info("Response Status: {}", response.getStatus());
            logger.info("Response Body: {}", convertResponseToString(response));
        }
    }

    private String convertObjectToJson(Object object) {
        try {
            logger.info("Converting object to JSON: {}", object);
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("Error converting object to JSON: {}", e.getMessage());
            return null;
        }
    }


    private String convertResponseToString(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            ContentCachingResponseWrapper wrapper = (ContentCachingResponseWrapper) response;
            try {
                wrapper.copyBodyToResponse();
                return new String(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (IOException e) {
                logger.error("Error converting response to string: {}", e.getMessage());
                return null;
            }
        }
        return null;
    }
}
