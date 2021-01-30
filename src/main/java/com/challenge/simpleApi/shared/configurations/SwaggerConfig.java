
package com.challenge.simpleApi.shared.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .useDefaultResponseMessages(false)
      .select()
      .apis(
        RequestHandlerSelectors.basePackage("com.challenge.simpleApi.domains.users")
      )
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo());
  }

  private Contact contact() {
    return new Contact(
      "Matheus Vargem",
      "https://github.com/busycore",
      "matheusvrgm@gmail.com"
    );
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title("Simple SpringBootApi")
      .description(
        "A Simple API made with Spring boot, to be used as a handbook"
      )
      .version("1.0.0")
      .contact(contact())
      .build();
  }
}

