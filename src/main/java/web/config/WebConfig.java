package web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import web.model.Post;
import web.model.UserContext;
import web.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan(basePackages = "web")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ConversionService conversionService;

    /*
    @Bean
    public ViewResolver myViewResolver() {
        return new BeanNameViewResolver();
    }
    */

    @Bean
    //@RequestScope
    @SessionScope //  a bean élete a sessionhoz lesz kötve
    // for demo purposes only!
    public UserContext userContext(UserService userService, HttpServletRequest request) {
        long userId = Optional.ofNullable(request.getParameter("userId"))
                .map(Long::valueOf)
                .orElse(-1L);
        UserContext userContext = new UserContext();
        userContext.setCurrentUser(userService.getUserById(userId).orElse(null));
        return userContext;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(Post.class, String.class, Post::toString);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ObjectToStringHttpMessageConverter(conversionService, StandardCharsets.UTF_8));
    }
}
