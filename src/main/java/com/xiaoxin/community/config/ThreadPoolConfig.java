package com.xiaoxin.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling // 开启定时任务
@EnableAsync
public class ThreadPoolConfig {
}
