package com.saltlux.jiphyeonjeon.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig extends AsyncConfigurerSupport {

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); //기본적으로 실행을 대기하고 있는 Thread의 갯수
        executor.setMaxPoolSize(30); //동시 동작하는, 최대 Thread 갯수
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("TASK-ASYNC-");
        executor.initialize();

        return executor;
    }
}
