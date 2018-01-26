package com.nbh.springmvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        Optional<HttpMessageConverter<?>> converter = converters.stream().filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();
        if (converter.isPresent()){
            AbstractJackson2HttpMessageConverter conv = (AbstractJackson2HttpMessageConverter) converter.get();
            conv.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            conv.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }

   /* @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/static", "html");
    }*/
}
