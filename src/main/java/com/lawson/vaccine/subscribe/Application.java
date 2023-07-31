package com.lawson.vaccine.subscribe;

import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.springboot.annotation.ForestScan;
import com.lawson.vaccine.subscribe.dto.AESConfigDTO;
import com.lawson.vaccine.subscribe.http.api.YueMiaoApi;
import com.lawson.vaccine.subscribe.http.vo.OrderVO;
import com.lawson.vaccine.subscribe.utils.GenericIdUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
@ForestScan(basePackages = "com.lawson.vaccin.subscribe.http.api")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
    }

    public static void test1(YueMiaoApi api) {
        OrderVO orderVO = new OrderVO();
        orderVO.setBirthday("1996-11-18");
        orderVO.setTel("19949592320");
        orderVO.setSex(2);
        orderVO.setCname("陈琳");
        orderVO.setDoctype(1);
        orderVO.setIdcard("511303199611185428");
        orderVO.setMxid("St4Lk05aELWowkjjYR2sgpFhRXhT7gYOPs2omZ9JBqE");
        orderVO.setDate("2023-07-31");
        orderVO.setPid("mQ4AAA");
        orderVO.setFtime(1);
        orderVO.setGuid("");
        String jsonStr = JSONObject.toJSONString(orderVO);
        System.out.println(jsonStr);
        AESConfigDTO aesConfigDTO = new AESConfigDTO()
                .setKey("4fc27a4c4421c9fe");
        AES aes = new AES("CBC", "PKCS7Padding", aesConfigDTO.getKey().getBytes(), aesConfigDTO.getIv().getBytes());
        String s = aes.encryptHex(jsonStr);
        System.out.println(s);
        String res = api.orderPost(s, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2OTA2NDg4MzYuMzYwMjkyLCJleHAiOjE2OTA2NTI0MzYuMzYwMjkyLCJzdWIiOiJZTlcuVklQIiwianRpIjoiMjAyMzA3MzAwMDM5MDUiLCJ2YWwiOiJhWFRSQVFJQUFBQUVibTl1WlJ4dmNYSTFielZQVVVWcmFrRnplbEYxU1ZwYVEwd3lXVEF0ZVZaVkFCeHZWVEkyV0hSNFJEUTRjelJWXHJcblUxZ3hSVk5FVFZWSGNVWkZlamt3RGpFeE55NHhOek11TnpNdU1qTXlBQ3RUZERSTWF6QTFZVVZNVjI5M2EycHFXVkl5YzJkd1JtaFNcclxuV0doVU4yZFpUMUJ6TW05dFdqbEtRbkZGQVFBQUFBQT0ifQ.pryHp_t0P9jvNXqQyS0gR_9g8oobyin-VB6Xj_bx46c; path=/"
                , GenericIdUtils.zfsw()
        );
        System.out.println(res);
    }
}
