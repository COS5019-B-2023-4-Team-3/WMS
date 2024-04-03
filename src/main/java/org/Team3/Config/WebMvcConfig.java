package org.Team3.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * WebMvcConfig class provides configurations for Spring MVC, allowing customization
 * of various aspects of the MVC framework.
 *
 * This class is used to configure content negotiation to ensure that CSS files are served
 * with the correct MIME type of "text/css". Additionally, it handles the serving of static
 * resources such as CSS files using resource handlers.
 *
 * By separating configuration concerns into dedicated configuration classes like WebMvcConfig,
 * the codebase becomes cleaner and more organized.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configures content negotiation to ensure correct MIME types for different media types.
     *
     * @param configurer ContentNegotiationConfigurer object to configure content negotiation.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(true)  // Favor media type from request parameter
                .parameterName("mediaType")  // Specify the request parameter name
                .ignoreAcceptHeader(true)  // Ignore Accept header
                .useRegisteredExtensionsOnly(false)  // Allow media type based on file extensions
                .defaultContentType(MediaType.TEXT_HTML)  // Default content type for the application
                .mediaType("html", MediaType.TEXT_HTML)  // Media type for HTML
                .mediaType("json", MediaType.APPLICATION_JSON)  // Media type for JSON
                .mediaType("xml", MediaType.APPLICATION_XML)  // Media type for XML
                .mediaType("css", MediaType.valueOf("text/css")); // Media type for CSS
    }

    /**
     * Adds resource handlers for serving static resources such as CSS files.
     *
     * @param registry ResourceHandlerRegistry object to register resource handlers.
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new MyPathResourceResolver());
    }

    /**
     * Custom resource resolver class to determine the media type (MIME type) of the requested
     * resource based on its file extension.
     */
    private static class MyPathResourceResolver extends PathResourceResolver {
        /**
         * Determines the media type (MIME type) of the requested resource.
         *
         * @param resource PathResource object representing the requested resource.
         * @return MediaType object representing the media type of the resource.
         */
        protected MediaType getMediaType(PathResource resource) {
            return MediaType.valueOf("text/css");
        }
    }
}


