package io.github.shiyusen.jmu.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 调度入口
 * 定时获取机器和jvm的参数
 *
 * @author 石玉森
 * @create 2020-03-09 11:05
 **/
@Service
public class ScheduledStart {


    @Scheduled(cron = "${jmu.cron.cpu}")
    public void cpuScheduled() {
        System.out.println("获取cpu参数");
    }

    @Scheduled(cron = "${jmu.cron.mem}")
    public void memScheduled() {
        System.out.println("获取mem参数");
    }

    @Scheduled(cron = "${jmu.cron.disk}")
    public void diskScheduled() {
        System.out.println("获取mem参数");
    }

    @Scheduled(cron = "${jmu.cron.jvm}")
    public void jvmScheduled() {
        System.out.println("获取mem参数");
    }


}
