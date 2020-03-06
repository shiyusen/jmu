package io.github.shiyusen.jmu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 石玉森
 * @create 2020-03-06 16:38
 **/


@Configuration
@ComponentScan(
        basePackages = {"io.github.shiyusen.jmu"}
)
public class JmuCommonConfiguration {
    public JmuCommonConfiguration() {
    }
}
