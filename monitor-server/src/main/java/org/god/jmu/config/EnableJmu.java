package org.god.jmu.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author 石玉森
 * @create 2020-03-06 16:44
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({JmuConfiguration.class})
public @interface EnableJmu {
}
