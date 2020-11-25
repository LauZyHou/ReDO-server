package com.ecnu.redo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ACAO {

    public <T> T myFunc(Class<T> a) {
        try {
            return a.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");//允许任何域名
        config.setAllowCredentials(true);//允许Cookie
        config.addAllowedMethod("*");//允许任何方法
        config.addAllowedHeader("*");//允许任何头
        config.setMaxAge(1800l);//设置预检请求保持时间，避免频繁发送预检请求

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
//        ApplicationContext ctx=new AnnotationConfigApplicationContext();
//       ACAO a= ctx.getBean(ACAO.class);
        return new CorsFilter(configSource);
    }
}