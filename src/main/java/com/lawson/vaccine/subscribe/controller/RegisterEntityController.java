package com.lawson.vaccine.subscribe.controller;

import com.lawson.vaccine.subscribe.services.YuemiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Lawson
 * @since 2023-08-02
 */
@RestController
@RequestMapping("/register")
public class RegisterEntityController {

    @Autowired
    private YuemiaoService yuemiaoService;

    @GetMapping(path = "/{proxyId}/{uuid}")
    public String wxLoginQr(@PathVariable(name = "uuid") String uuid) {
        return this.yuemiaoService.getWxQr(uuid);
    }

}
