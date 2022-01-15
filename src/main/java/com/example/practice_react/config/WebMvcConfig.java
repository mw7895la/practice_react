package com.example.practice_react.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//FE의 CORS 설정
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry){

        //모든 경로에 대해서,
        registry.addMapping("/**")
                //특히 Origin이 http:localhost:3000"에 대해
                .allowedOrigins("http://localhost:3000")
                //GET , POST, PUT, PATCH, DELETE OPTIONS 메서드를 허용.
                .allowedMethods("GET","POST","PATCH","DELETE","OPTIONS")
                //모든 Header에 대한 것도 허용
                .allowedHeaders("*")
                //모든 인증에 관한 정보도 허용
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}
