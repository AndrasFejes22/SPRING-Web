package web.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import web.model.UserContext;
import web.service.UserService;

import java.util.Optional;

@Configuration
@ComponentScan(basePackages = "web")
public class WebConfig {

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
}
