package io.github.shiyusen.jmu.annotation;

import java.lang.annotation.*;

/**
 * method接口日志切面
 *
 * @author 石玉森
 * at created 2020-03-08 10:13
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Operationable {
    boolean alarm() default false;
}
