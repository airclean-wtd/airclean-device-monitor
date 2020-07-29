package com.sailbright.airclean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AytApiConfig {

    public static String API_TOKEN;
    public static String API_URL;

    @Value("${ayt.api-url}")
    public void setUrl(String url) {
        AytApiConfig.API_URL = url;
    }

    @Value("${ayt.api-token}")
    public void setToken(String token) {
        AytApiConfig.API_TOKEN = token;
    }

}
