package com.lawson.vaccine.subscribe.config;

import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lmax.disruptor.EventFactory;

public class YumiaoEventFactory implements EventFactory<UserDTO> {
    @Override
    public UserDTO newInstance() {
        return new UserDTO();
    }
}
