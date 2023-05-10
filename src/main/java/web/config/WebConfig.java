package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;

@Configuration
@ComponentScan(basePackages = "web")
public class WebConfig {

    @Bean
    public ViewResolver myViewResolver() {
        return new BeanNameViewResolver();
    }
}