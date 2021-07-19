package com.fangaoxs.lotteryserver.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/14/18:23
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public Docket docket(@Qualifier("apiInfo") ApiInfo apiInfo){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                //开启Swagger，默认是false
                .enable(!activeProfile.equals("prod"))
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                //扫描所有接口
                //.apis(RequestHandlerSelectors.any())
                //不扫描任何接口
                //.apis(RequestHandlerSelectors.none())
                //扫描控制器类方法上带有GetMapping注解的接口
                //.apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                //扫描控制器类上带有RequestMapping注解的接口
                //.apis(RequestHandlerSelectors.withClassAnnotation(RequestMapping.class))
                //扫描这个包里的接口
                .apis(RequestHandlerSelectors.basePackage("com.fangaoxs.lotteryserver"))
                //扫描这个请求路径的接口
                //.paths(PathSelectors.ant("/wqk/**"))
                .build();
    }
    //配置自定义作者信息apiInfo
    @Bean(name = "apiInfo")
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "wqk的swaggerAPI配置文档",
                "Api接口配置文档",
                "v1.3",
                "urn:tos",
                new Contact("wqk", "", "a954278478@gmail.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
