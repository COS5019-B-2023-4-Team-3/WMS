package org.Team3.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * provides callback methods that allow you to customize the Spring MVC configuration.
 * Used to configure content negotiation to ensure that CSS files are served with the correct MIME type of "text/css".
 * separating configuration concerns into dedicated configuration classes like WebMvcConfig helps maintain a cleaner
 * and more organized codebase.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

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
     * commonly used in Spring MVC applications to serve static resources such as CSS files, JavaScript files, images, etc.
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(0)
                .resourceChain(true)
                .addResolver(new MyPathResourceResolver());
    }

    /**
     * overridden to customize the behavior of the resource resolver.
     * determines the media type (MIME type) of the requested resource based on its file extension.
     * In this example, it always returns text/css for CSS resources.
     * Ensures our custom css styles are loaded in correctly
     */
    private static class MyPathResourceResolver extends PathResourceResolver {
        protected MediaType getMediaType(PathResource resource) {
            return MediaType.valueOf("text/css");
        }
    }
}

