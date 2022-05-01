package com.example.spring_project.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableWebMvc   // 3.0.0 에선 @EnableSwagger2 -> EnableWebMvc를 쓰자
public class SwaggerConfiguration {
    @Bean
    public Docket swaggerApi() {
        String version = "v1";
        String title = "Swagger API Document";

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo(title, version)).select() // 제목 설명 등에 필요한 문서 제공
                .apis(RequestHandlerSelectors.basePackage("com.example.spring_project.controller")) // controller mapping 된 resource 문서화
                .paths(PathSelectors.ant("/v1/**")) //apis에서 지정된 API 중 특정 path를 필터링해서 제공
                .build()
                .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }

    private ApiInfo swaggerInfo(String title, String version) {
        return new ApiInfoBuilder() // AppInfo를 builder를 이용해 정의 할 수 있음.
                .title(title)
                .version(version)
                .description("앱 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                .license("E1psycongr00").licenseUrl("http://daddyprogrammer.org").version("1").build();
    }
}
