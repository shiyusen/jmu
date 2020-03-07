package io.github.shiyusen.jmu.business.hook.impl;

import io.github.shiyusen.jmu.business.hook.JmuAlarmHook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ServiceLoader;

/**
 * @author 石玉森
 * @create 2020-03-07 20:11
 **/
@Slf4j
@Service
public class JmuAlarmHookService implements JmuAlarmHook {

    private ServiceLoader<JmuAlarmHook> jmuAlarmHookServiceLoader;

    public JmuAlarmHookService() {
        this.jmuAlarmHookServiceLoader = ServiceLoader.load(JmuAlarmHook.class);
        System.out.println(jmuAlarmHookServiceLoader);
    }

    @Override
    public void alarm(Object input, Object output) {
        jmuAlarmHookServiceLoader.forEach(jmuAlarmHook -> {
            try {
                jmuAlarmHook.alarm(input, output);
            } catch (Exception e) {
                log.error("alarm notify error={}", e.getMessage(), e);
            }
        });
    }
}
