package io.github.shiyusen.jmu.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * @author 石玉森
 * @create 2020-03-07 19:53
 **/
@Configuration
public class JmuPropertySourcesConfigurer {
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        ClassPathResource[] resources = new ClassPathResource[]{new ClassPathResource("jmu.yml"),new ClassPathResource("application-jmu.yml")};
        yaml.setResources(resources);
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
