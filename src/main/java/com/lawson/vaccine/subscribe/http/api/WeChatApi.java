package com.lawson.vaccine.subscribe.http.api;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.converter.json.FastjsonEncoder;
import com.lawson.vaccine.subscribe.http.vo.wechat.*;

@BaseRequest(baseURL = "https://api.cn2030.com")
@FastjsonEncoder
public interface WeChatApi {

    /**
     * 创建实例
     */
    @Post(url = "/api/Client/WXCreate")
    WeChatR<CreateWXVO> createWX();

    /**
     * 设置代理
     */
    @Post(url = "/api/Client/WXSetProxy")
    WeChatR<Void> setProxyWX(@JSONBody SetProxyWXParam data);

    /**
     * 释放实例
     */
    @Post(url = "/api/Client/WXRelease")
    WeChatR<Void> releaseWX(@JSONBody CreateWXVO data);

    /**
     * 获取登录二维码
     */
    @Post(url = "/api/Login/WXGetLoginQrcode")
    WeChatR<WXQrCodeVO> getLoginQrcodeWX(@JSONBody CreateWXVO data);

    /**
     * 获取登录二维码状态（含账号密码）
     */
    @Post(url = "/api/Login/WXCheckLoginQrcode")
    WeChatR<LoginQrcodeStatusVO> WXCheckLoginQrcode(@JSONBody LoginQrcodeStatusParam data);

    /**
     * 登录微信
     */
    @Post(url = "/api/Login/WXLoginManual")
    WeChatR<WXLoginManualVO> WXLoginManual(@JSONBody WXLoginManualParam data);

    /**
     * 注销登录微信
     */
    @Post(url = "/api/Login/WXLogout")
    WeChatR<Void> WXLogout(@JSONBody CreateWXVO data);

    /**
     * 获取微信小程序登录code
     */
    @Post(url = "/api/Auth/WXJSLogin")
    WeChatR<WXJSLoginVO> WXJSLogin(@JSONBody WXJSLoginParam data);

    /**
     * 获取微信小程序授权信息
     */
    @Post(url = "/api/Auth/WXJSOperateWxData")
    WeChatR<WXJSLoginVO> WXJSOperateWxData(@JSONBody WXJSLoginParam data);
}
