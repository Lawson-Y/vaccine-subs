package com.lawson.vaccine.subscribe;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.springboot.annotation.ForestScan;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.http.api.YueMiaoApi;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.OrderVO;
import com.lawson.vaccine.subscribe.services.YuemiaoService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ForestScan(basePackages = "com.lawson.vaccine.subscribe.http.api")
@MapperScan(basePackages = "com.lawson.vaccine.subscribe.mapper")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        YuemiaoService bean = run.getBean(YuemiaoService.class);
    }

    public static void test1(YueMiaoApi api) {
        OrderVO orderVO = new OrderVO();
        orderVO.setBirthday("1996-11-18");
        orderVO.setTel("19949592320");
        orderVO.setSex(2);
        orderVO.setCname("陈琳");
        orderVO.setDoctype(1);
        orderVO.setIdcard("511303199611185428");
        orderVO.setMxid("pMfKv69ZC3IWpcZBkkgun6SAN5nxnyFh6FiIPPqJGxI");
        orderVO.setDate("2023-07-31");
        orderVO.setPid("mQ4AAA");
        orderVO.setFtime(1);
        orderVO.setGuid("");
        String jsonStr = JSONObject.toJSONString(orderVO);
        System.out.println(jsonStr);
        UserDTO userDTO = new UserDTO()
                .setKey("3bdae3a26dd2ec1a");
        String s = userDTO.encrypt(orderVO);
        System.out.println(s);
    }
}
