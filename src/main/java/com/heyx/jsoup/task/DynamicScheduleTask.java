package com.heyx.jsoup.task;

import com.heyx.jsoup.entity.dot.Socks;
import com.heyx.jsoup.service.dot.SocksSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 *Cron表达式参数分别表示：
 *
 * 秒（0~59） 例如0/5表示每5秒
 * 分（0~59）
 * 时（0~23）
 * 日（0~31）的某天，需计算
 * 月（0~11）
 * 周几（ 可填1-7 或 SUN/MON/TUE/WED/THU/FRI/SAT）
 *
 * @author: heyx
 * @create: 2019-04-11 18:54
 * @email; 1064042411@qq.com
 */
@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    SocksSerivce socksSerivce;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                },
                triggerContext -> {
                    Socks socks = socksSerivce.findFirstByName("定时任务一");
                    if (socks == null){
                        return null;
                    }
                    String cron = socks.getCron();
                    if (StringUtils.isEmpty(cron)) {
                        return null;
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
