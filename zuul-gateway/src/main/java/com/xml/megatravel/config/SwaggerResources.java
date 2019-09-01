package com.xml.megatravel.config;

import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Primary
@Configuration
@EnableSwagger2
public class SwaggerResources implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public SwaggerResources(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        final List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("zuul-gateway", "/v2/api-docs", "2.0"));

        routeLocator.getRoutes().forEach(
                r -> resources.add(swaggerResource(r.getId(), r.getFullPath().replace("**", "v2/api-docs"), "2.0"))
        );

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
