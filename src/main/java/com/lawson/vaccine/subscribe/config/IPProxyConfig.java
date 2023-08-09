package com.lawson.vaccine.subscribe.config;


import kdl.Auth;
import kdl.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class IPProxyConfig {
    Logger log = LoggerFactory.getLogger(IPProxyConfig.class);

    @Bean
    public Client clientProxy() throws Exception {
        Auth auth = new Auth("ot2sl3dox0fu6uiqhddt", "4e1ijq7ceocin1basu40jqwtban702fc");
        Client client = new Client(auth);
        return client;
    }

}
