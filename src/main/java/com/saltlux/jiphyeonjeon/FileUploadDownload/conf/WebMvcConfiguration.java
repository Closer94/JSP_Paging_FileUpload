package com.saltlux.jiphyeonjeon.FileUploadDownload.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF-8"); //파일 인코딩을 UTF-8 로 설정
        commonsMultipartResolver.setMaxUploadSizePerFile(500 * 1204 * 1024); //업로드 되는 파일 크기를 제한(바이트 단위 설정이므로 여기서는 500MB 제한을 의미)

        return commonsMultipartResolver;
    }


}
