package com.get.hyphenbackenduser.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Hyphen User API Doc",
                description = "This is Hyphen user API document.",
                version = "v1.0.0",
//                license = @License(
//                        name = "Apache License Version 2.0",
//                        url = "http://www.apache.org/licenses/LICENSE-2.0"
//                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                ),
                contact = @Contact(
                        name = "GET team"
                )
        )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(Arrays.asList(securityRequirement));
    }

    @Bean
    public GroupedOpenApi adminGroup() {
        List<Tag> tags = List.of(
                new Tag().name("InquiryController").description("<b>[문의]</b> 문의 API")
        );
        String[] pathsToMatch = {"/api/inquiries/**"};
        String[] pathsToExclude = {"/api/auth/**", "/api/user/**", "/api/mail/**", "/api/inquiry/**"};
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi managerGroup() {
        List<Tag> tags = List.of();
        String[] pathsToMatch = {};
        String[] pathsToExclude = {"/api/auth/**", "/api/user/**", "/api/inquiry/**", "/api/inquiries/**", "/api/mail/**"};
        return GroupedOpenApi.builder()
                .group("manager")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }

    @Bean
    public GroupedOpenApi memberGroup() {
        List<Tag> tags = List.of(
                new Tag().name("AuthController").description("<b>[회원]</b> 인증 API"),
                new Tag().name("ValidateController").description("<b>[회원]</b> 인가 API"),
                new Tag().name("UserController").description("<b>[회원]</b> 관리 API"),
                new Tag().name("MailController").description("<b>[메일]</b> 인증 API"),
                new Tag().name("InquiryController").description("<b>[문의]</b> 문의 API")
        );
        String[] pathsToMatch = {"/api/auth/**", "/api/user/**", "/api/inquiry/**", "/api/mail/**"};
        String[] pathsToExclude = {"/api/inquiries/**"};
        return GroupedOpenApi.builder()
                .group("member")
                .pathsToMatch(pathsToMatch)
                .pathsToExclude(pathsToExclude)
                .addOpenApiCustomizer(openApi -> {
                    openApi.setTags(tags);
                })
                .build();
    }
}
