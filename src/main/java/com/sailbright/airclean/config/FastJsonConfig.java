package com.sailbright.airclean.config;

import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfig {

    @Bean
    public ParserConfig getParserConfig() {
        ParserConfig pc = ParserConfig.getGlobalInstance();
        pc.setAsmEnable(false);
        return pc;
    }

}
