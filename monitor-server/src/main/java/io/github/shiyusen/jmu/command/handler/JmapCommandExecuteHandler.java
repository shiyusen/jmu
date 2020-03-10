package io.github.shiyusen.jmu.command.handler;

import io.github.shiyusen.jmu.command.CommandExecuteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * @author 石玉森
 * @create 2020-03-09 14:31
 **/
@Slf4j
public class JmapCommandExecuteHandler extends CommandExecuteHandler {

    @Value("${jmu.dump.jmap.root-path:/var/log/jmu/jmap/}")
    private String dumpRootPath;

    public String dumpJmap(String id) throws IOException {
        dumpRootPath = dumpRootPath + "dump_" + id + System.currentTimeMillis();
        String s = execute(new String[]{"jmap"," -dump:live,format=b,file=" + dumpRootPath, id});
        log.info("jmap dump pid={},path={}", id, dumpRootPath);
        return dumpRootPath;
    }

}
