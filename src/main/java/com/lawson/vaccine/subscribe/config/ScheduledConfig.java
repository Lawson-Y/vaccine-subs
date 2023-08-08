package com.lawson.vaccine.subscribe.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableScheduling
@EnableAsync
public class ScheduledConfig {

    @Bean
    public Executor myAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //最大线程数
        executor.setMaxPoolSize(100);
        //核心线程数
        executor.setCorePoolSize(10);
        //任务队列的大小
        executor.setQueueCapacity(2000);
        //线程前缀名
        executor.setThreadNamePrefix("lawosn-job-");
        //线程存活时间
        executor.setKeepAliveSeconds(100 * 60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        //线程初始化
        executor.initialize();
        return executor;
    }


}
