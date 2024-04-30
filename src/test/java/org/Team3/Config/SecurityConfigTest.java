package org.Team3.Config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Test
    void configureContentNegotiation_setsCorrectMediaTypes() {
        ContentNegotiationConfigurer configurer = mock(ContentNegotiationConfigurer.class);

        // Stub the methods to return the mock itself
        when(configurer.favorParameter(anyBoolean())).thenReturn(configurer);
        when(configurer.parameterName(anyString())).thenReturn(configurer);
        when(configurer.ignoreAcceptHeader(anyBoolean())).thenReturn(configurer);
        when(configurer.useRegisteredExtensionsOnly(anyBoolean())).thenReturn(configurer);
        when(configurer.defaultContentType(any())).thenReturn(configurer);
        when(configurer.mediaType(anyString(), any())).thenReturn(configurer);

        WebMvcConfig webMvcConfig = new WebMvcConfig();
        webMvcConfig.configureContentNegotiation(configurer);

        verify(configurer).favorParameter(true);
        verify(configurer).parameterName("mediaType");
        verify(configurer).ignoreAcceptHeader(true);
        verify(configurer).useRegisteredExtensionsOnly(false);
        verify(configurer).defaultContentType(MediaType.TEXT_HTML);
        verify(configurer).mediaType("html", MediaType.TEXT_HTML);
        verify(configurer).mediaType("json", MediaType.APPLICATION_JSON);
        verify(configurer).mediaType("xml", MediaType.APPLICATION_XML);
        verify(configurer).mediaType("css", MediaType.valueOf("text/css"));
    }

@Test
void addResourceHandlers_registersCorrectResourceHandlers() {
    ResourceHandlerRegistry registry = mock(ResourceHandlerRegistry.class);
    ResourceHandlerRegistration registration = mock(ResourceHandlerRegistration.class);
    ResourceChainRegistration chainRegistration = mock(ResourceChainRegistration.class);

    // Stub the addResourceHandler method to return the mock registration
    when(registry.addResourceHandler("/static/css/**")).thenReturn(registration);
    // Stub the addResourceLocations method to return the mock registration
    when(registration.addResourceLocations("classpath:/static/css/")).thenReturn(registration);
    // Stub the setCachePeriod method to return the mock registration
    when(registration.setCachePeriod(anyInt())).thenReturn(registration);
    // Stub the resourceChain method to return the mock chainRegistration
    when(registration.resourceChain(anyBoolean())).thenReturn(chainRegistration);

    WebMvcConfig webMvcConfig = new WebMvcConfig();
    webMvcConfig.addResourceHandlers(registry);

    verify(registry).addResourceHandler("/static/css/**");
    verify(registration).addResourceLocations("classpath:/static/css/");
}
}