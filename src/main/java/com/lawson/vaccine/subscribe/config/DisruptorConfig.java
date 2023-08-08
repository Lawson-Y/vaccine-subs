package com.lawson.vaccine.subscribe.config;

import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkProcessor;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class DisruptorConfig {

    @Bean
    public RingBuffer<UserDTO> yueMiaoMQ() {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        int buffSize = 1024 * 4;
        Disruptor<UserDTO> disruptor = new Disruptor<>(new YumiaoEventFactory(), buffSize, threadPool, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new YuemiaoEventHandler());
        disruptor.start();
        return disruptor.getRingBuffer();
    }

}
