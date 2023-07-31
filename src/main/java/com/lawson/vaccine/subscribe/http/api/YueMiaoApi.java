package com.lawson.vaccine.subscribe.http.api;

import com.dtflys.forest.annotation.*;
import com.lawson.vaccine.subscribe.http.vo.CustomerListQueryVO;

@BaseRequest(baseURL = "https://api.cn2030.com")
public interface YueMiaoApi {

    /**
     * 提交订单
     *
     * @param data
     * @param cookie
     * @param zftsl
     * @return
     */
    @Post(url = "/sc/api/User/OrderPost", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String orderPost(@JSONBody String data, @Var(value = "cook") String cookie, @Var(value = "zftsl") String zftsl);

    /**
     * 获取验证码
     *
     * @param mxid
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCaptcha}", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String getCaptcha(@Query(name = "mxid") String mxid, @Var(value = "cook") String cookie, @Var(value = "zftsl") String zftsl);

    /**
     * 日期条件获取详细
     *
     * @param pid
     * @param id
     * @param date
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCustSubscribeDateDetail", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String getCustSubscribeDateDetail(@Query(name = "pid") String pid,
                                      @Query(name = "id") Integer id,
                                      @Query(name = "scdate") String date,
                                      @Var(value = "cook") String cookie,
                                      @Var(value = "zftsl") String zftsl);

    /**
     * 月份条件获取详细列表（既可预约日期列表）
     *
     * @param pid
     * @param id
     * @param month
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCustSubscribeDateAll", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String getCustSubscribeDateAll(@Query(name = "pid") String pid,
                                   @Query(name = "id") Integer id,
                                   @Query(name = "month") String month,
                                   @Var(value = "cook") String cookie,
                                   @Var(value = "zftsl") String zftsl);

    /**
     * 获取某医院产品列表
     *
     * @param id
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=CustomerProduct", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String getCustSubscribeDateAll(@Query(name = "id") Integer id,
                                   @Var(value = "cook") String cookie,
                                   @Var(value = "zftsl") String zftsl);

    /**
     * 获取用户信息
     *
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=User", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String user(@Var(value = "cook") String cookie,
                @Var(value = "zftsl") String zftsl);

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
    String auth(@Query(name = "code") String code,
                @JSONBody(name = "rawdata") String rawdata,
                @Var(value = "zftsl") String zftsl);

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
    String auth(@Query CustomerListQueryVO queryVO,
                @Var(value = "zftsl") String zftsl);

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
    String getCat2(@Query(name = "id") Integer id,
                   @Var(value = "zftsl") String zftsl);

    /**
     * 获取疫苗列表
     *
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetCat1", headers = {
            "zftsl: ${zftsl}"
    })
    String getCat1(@Var(value = "zftsl") String zftsl);

    /**
     * 获取订单状态
     *
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=GetOrderStatus", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String getOrderStatus(@Var(value = "cook") String cookie,
                          @Var(value = "zftsl") String zftsl);

    /**
     * 获取用户订单列表
     *
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=UserSubcribeList", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String userSubcribeList(@Var(value = "cook") String cookie,
                            @Var(value = "zftsl") String zftsl);

    /**
     * 获取订单详情
     *
     * @param id     订单列表的订单id
     * @param cookie
     * @param zftsl
     * @return
     */
    @Get(url = "/sc/wx/HandlerSubscribe.ashx?act=UserSubcribeDetail", headers = {
            "Cookie: ASP.NET_SessionId=${cook}",
            "zftsl: ${zftsl}"
    })
    String userSubcribeList(@Query(name = "id") String id,
                            @Var(value = "cook") String cookie,
                            @Var(value = "zftsl") String zftsl);
}
