package org.god.jmu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 石玉森
 * @create 2018-11-16 16:57
 **/

public class LogUtil {

    private static ConcurrentHashMap<String, Logger> loggerMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        for (int index = 0; index < stackTraceElement.length; index++) {
            StackTraceElement e = stackTraceElement[index];
        }
    }

    /**
     * 性能差，不建议使用
     *
     * @return
     */
    public static Logger getInstance() {
//        new Exception().getStackTrace()[1]
        StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
        String superCallerClassName = stackTraceElement[2].getClassName();
        if (!loggerMap.containsKey(superCallerClassName)) {
            Logger logger = LoggerFactory.getLogger(superCallerClassName);
            loggerMap.put(superCallerClassName, logger);
        }
        return loggerMap.get(superCallerClassName);
    }

    public static Logger getInstance(Class clazz) {
        String clazzName = clazz.getSimpleName();
        if (loggerMap.containsKey(clazzName)) {
            return loggerMap.get(clazzName);
        }
        Logger logger = LoggerFactory.getLogger(clazzName);
        loggerMap.put(clazzName, logger);
        return logger;
    }

    public static Logger getInstance(Object target) {
        return getInstance(target.getClass());
    }

}
