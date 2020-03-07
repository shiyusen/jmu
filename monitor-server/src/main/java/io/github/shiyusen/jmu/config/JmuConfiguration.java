package io.github.shiyusen.jmu.config;

import io.github.shiyusen.jmu.business.test.controller.JvmController;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerMapping;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.JsonSerializer;

/**
 * @author 石玉森
 * @create 2020-03-06 16:23
 **/

@Configuration
@Import({SpringfoxWebMvcConfiguration.class, JmuCommonConfiguration.class})
@ComponentScan(
        basePackages = {"io.github.shiyusen.jmu"}
)
//@ConditionalOnWebApplication
public class JmuConfiguration {
    public JmuConfiguration() {
    }

    /**
     * 借用swagger2的
     *
     * @return
     */
    @Bean
    public JacksonModuleRegistrar swagger2Module() {
        return new JmuJacksonModule();
    }

    @Bean
    public HandlerMapping jmuControllerMapping(Environment environment, DocumentationCache documentationCache, JsonSerializer jsonSerializer) {
        return new PropertySourcedRequestMappingHandlerMapping(environment, new JvmController(environment, jsonSerializer));
    }
}
