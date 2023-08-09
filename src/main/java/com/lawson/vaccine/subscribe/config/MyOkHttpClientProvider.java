package com.lawson.vaccine.subscribe.config;

import com.dtflys.forest.backend.okhttp3.OkHttpClientProvider;
import com.dtflys.forest.handler.LifeCycleHandler;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.mapping.MappingVariable;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class MyOkHttpClientProvider implements OkHttpClientProvider {
    private final Logger log = LoggerFactory.getLogger(MyOkHttpClientProvider.class);

    Authenticator proxyAuthenticator = (route, response) -> {
        String credential = Credentials.basic("d3008949239", "3lf6eu1f");
        return response.request().newBuilder()
                .header("Proxy-Authorization", credential)
                .build();
    };

    private static final Map<String, OkHttpClient> cache = new ConcurrentHashMap<>();

    @Override
    public OkHttpClient getClient(ForestRequest request, LifeCycleHandler lifeCycleHandler) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .followRedirects(false)
                .retryOnConnectionFailure(false)
                .followRedirects(false);
        MappingVariable proxy = request.getMethod().getVariable("proxy");
        if (Objects.nonNull(proxy)) {
            Object value = request.getArguments()[proxy.getIndex()];
            if (Objects.nonNull(value)) {
                String proxyIp = value.toString();
                if (proxyIp.length() > 5) {
                    if (cache.containsKey(proxyIp)) {
                        return cache.get(proxyIp);
                    }
                    String[] ipPort = proxyIp.split(":");
                    builder.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ipPort[0], Integer.parseInt(ipPort[1]))));
                    log.info("代理主机：{}:{}", ipPort[0], ipPort[1]);
                    OkHttpClient httpClient = builder.build();
                    cache.put(proxyIp, httpClient);
                    return httpClient;
                }
            }
        }
        OkHttpClient httpClient = builder.build();
        final String key = "ok;" + request.clientKey();
        cache.put(key, httpClient);
        return httpClient;
    }
}
