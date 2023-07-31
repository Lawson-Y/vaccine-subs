package com.lawson.vaccine.subscribe.http.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;

@BaseRequest(baseURL = "https://api.cn2030.com")
public interface YueMiaoApi {

    @Post(url = "/sc/api/User/OrderPost", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String orderPost(@JSONBody String data, @Var(value = "cook") String cookie, @Var(value = "zftsl") String zftsl);

}
