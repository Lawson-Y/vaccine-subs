package com.lawson.vaccine.subscribe.config;

import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lmax.disruptor.EventHandler;

public class YuemiaoEventHandler implements EventHandler<UserDTO> {
    @Override
    public void onEvent(UserDTO event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event);
    }
}
