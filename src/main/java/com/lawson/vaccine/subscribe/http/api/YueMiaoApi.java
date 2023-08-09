package com.lawson.vaccine.subscribe.http.api;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.backend.okhttp3.OkHttp3;
import com.dtflys.forest.http.ForestResponse;
import com.lawson.vaccine.subscribe.config.MyOkHttpClientProvider;
import com.lawson.vaccine.subscribe.dto.UserDTO;
import com.lawson.vaccine.subscribe.http.vo.yuemiao.CustomerListQueryVO;

@BaseRequest(baseURL = "https://api.cn2030.com",
        userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF XWEB/8287",
        headers = {"referer: https://servicewechat.com/wx2c7f0f3c30d99445/101/page-frame.html"})
@OkHttp3(client = MyOkHttpClientProvider.class)
@BackendClient(cache = false)
public interface YueMiaoApi {

    /**
     * 提交订单
     *
     * @param data
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Post(url = "/sc/api/User/OrderPost", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> orderPost(@JSONBody String data, @Var(value = "userDTO") UserDTO userDTO, @Var(value = "zftsl") String zftsl, @Var(value = "proxy") String proxy);

    /**
     * 获取验证码
     *
     * @param mxid
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCaptcha", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> getCaptcha(@Query(name = "mxid") String mxid, @Var(value = "userDTO") UserDTO userDTO, @Var(value = "zftsl") String zftsl, @Var(value = "proxy") String proxy);

    /**
     * 日期条件获取详细
     *
     * @param pid
     * @param id
     * @param date
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCustSubscribeDateDetail", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> getCustSubscribeDateDetail(@Query(name = "pid") String pid,
                                                      @Query(name = "id") Integer id,
                                                      @Query(name = "scdate") String date,
                                                      @Var(value = "userDTO") UserDTO userDTO,
                                                      @Var(value = "zftsl") String zftsl,
                                                      @Var(value = "proxy") String proxy);

    /**
     * 月份条件获取详细列表（既可预约日期列表）
     *
     * @param pid
     * @param id
     * @param month
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCustSubscribeDateAll", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> getCustSubscribeDateAll(@Query(name = "pid") String pid,
                                                   @Query(name = "id") Integer id,
                                                   @Query(name = "month") String month,
                                                   @Var(value = "userDTO") UserDTO userDTO,
                                                   @Var(value = "zftsl") String zftsl,
                                                   @Var(value = "proxy") String proxy);

    /**
     * 获取某医院产品列表
     *
     * @param id
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=CustomerProduct", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> customerProduct(@Query(name = "id") Integer id,
                                           @Var(value = "userDTO") UserDTO userDTO,
                                           @Var(value = "zftsl") String zftsl,
                                           @Var(value = "proxy") String proxy);

    /**
     * 获取用户信息
     *
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=User", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> user(@Var(value = "userDTO") UserDTO userDTO,
                                @Var(value = "zftsl") String zftsl,
                                @Var(value = "proxy") String proxy);

    /**
     * 用户认证授权
     *
     * @param code
     * @param rawdata
     * @param zftsl
     * @return
     */
    @Post(url = "/sc/wx/HandlerSubscribe.ashx?act=auth", headers = {
            "zftsl: ${zftsl}"
    })
    ForestResponse<String> auth(@Query(name = "code") String code,
                                @JSONBody(name = "rawdata") String rawdata,
                                @Var(value = "zftsl") String zftsl,
                                @Var(value = "proxy") String proxy);

    /**
     * 获取机构列表
     *
     * @param queryVO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=CustomerList", headers = {
            "zftsl: ${zftsl}"
    })
    ForestResponse<String> customerList(@Query CustomerListQueryVO queryVO,
                                        @Var(value = "zftsl") String zftsl,
                                        @Var(value = "proxy") String proxy);

    /**
     * 获取疫苗分类列表
     *
     * @param id
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCat2", headers = {
            "zftsl: ${zftsl}"
    })
    ForestResponse<String> getCat2(@Query(name = "id") Integer id,
                                   @Var(value = "zftsl") String zftsl,
                                   @Var(value = "proxy") String proxy);

    /**
     * 获取疫苗列表
     *
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCat1", headers = {
            "zftsl: ${zftsl}"
    })
    ForestResponse<String> getCat1(@Var(value = "zftsl") String zftsl,
                                   @Var(value = "proxy") String proxy);

    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCat1", headers = {
            "zftsl: ${zftsl}"
    })
    ForestResponse<String> getCat1Test(@Var(value = "zftsl") String zftsl, @Var(value = "proxy") String proxy);

    /**
     * 获取订单状态
     *
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetOrderStatus", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> getOrderStatus(@Var(value = "userDTO") UserDTO userDTO,
                                          @Var(value = "zftsl") String zftsl,
                                          @Var(value = "proxy") String proxy);

    /**
     * 获取用户订单列表
     *
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=UserSubcribeList", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> userSubcribeList(@Var(value = "userDTO") UserDTO userDTO,
                                            @Var(value = "zftsl") String zftsl,
                                            @Var(value = "proxy") String proxy);

    /**
     * 获取订单详情
     *
     * @param id      订单列表的订单id
     * @param userDTO
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=UserSubcribeDetail", headers = {
            "Cookie: ASP.NET_SessionId=${userDTO.cookie}",
            "zftsl: ${zftsl}"
    })
//    @HTTPProxy(host = "${userDTO.userProxy.host}", port = "${userDTO.userProxy.port}", username = "${userDTO.userProxy.uname}", password = "${userDTO.userProxy.passwd}")
    ForestResponse<String> userSubcribeDetail(@Query(name = "id") String id,
                                              @Var(value = "userDTO") UserDTO userDTO,
                                              @Var(value = "zftsl") String zftsl,
                                              @Var(value = "proxy") String proxy);
}
