package io.github.shiyusen.jmu.command.handler;

import io.github.shiyusen.jmu.command.CommandExecuteHandler;
import io.github.shiyusen.jmu.util.ArrayUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author 石玉森
 * @create 2020-03-09 14:31
 **/

public class JstackCommandExecuteHandler extends CommandExecuteHandler {
    private final static String prefix = "java.lang.Thread.State: ";

    @Value("${jmu.dump.jstack.root-path}")
    private String dumpRootPath;
    /**
     * 该进程的线程信息
     * X轴为时间，Y轴为值的变化
     *
     * @param id
     */
    public void jstack(String id) {
        String s = execute(new String[]{"jstack", id});
        int threadTotal = ArrayUtil.appearNumber(s, "nid=");
        int runnableThreadNum = ArrayUtil.appearNumber(s, prefix + "RUNNABLE");
        int timedThreadNum = ArrayUtil.appearNumber(s, prefix + "TIMED_WAITING");
        int waitingThreadNum = ArrayUtil.appearNumber(s, prefix + "WAITING");
    }

    /**
     * 导出线程快照
     *
     * @param id
     */
    public String dumpJstack(String id) throws IOException {
        String path = dumpRootPath+"dump/" + id + "_thread.txt";
        String s = execute(new String[]{"jstack", id});
        File file = new File(path);
        FileUtils.write(file, s, Charset.forName("UTF-8"));
        return path;
    }
}
