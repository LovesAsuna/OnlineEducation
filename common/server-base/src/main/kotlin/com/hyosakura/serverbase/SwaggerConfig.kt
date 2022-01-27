package com.hyosakura.serverbase

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.oas.annotations.EnableOpenApi
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


/**
 * @author LovesAsuna
 **/
@Configuration
@EnableOpenApi
open class SwaggerConfig {
    @Bean
    open fun webApiConfig(): Docket = Docket(DocumentationType.OAS_30)
        .groupName("webApi")
        .apiInfo(webApiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.hyosakura"))
        .paths(PathSelectors.any())
        .build()


    private fun webApiInfo(): ApiInfo = ApiInfoBuilder()
        .title("API文档")
        .description("本文档描述了中心微服务接口定义")
        .version("1.0")
        .contact(Contact("LovesAuna", "https://hyosakura.com", "qq625924077@gmail.com"))
        .build()


}