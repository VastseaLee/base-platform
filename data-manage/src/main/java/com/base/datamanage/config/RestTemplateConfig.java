package com.base.datamanage.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Autowired
    private HttpClientPoolConfig httpClientPoolConfig;

//    @Bean
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate;
//    }


    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return createRestTemplate(factory);
    }

    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        //1.???????????????httpclient???RestTemplate
        RestTemplate restTemplate = new RestTemplate(factory);
        //2.??????RestTemplate?????????MessageConverter
        //????????????StringHttpMessageConverter????????????????????????????????????
        modifyDefaultCharset(restTemplate);
        //3.?????????????????????
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }

    /**
     * ??????HTTP???????????????
     */
    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        //2.??????clientHttpRequestFactory
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
                HttpComponentsClientHttpRequestFactory(httpClient());
        // ????????????
        clientHttpRequestFactory.setConnectTimeout(httpClientPoolConfig.getConnectTimeout());
        // ??????????????????????????????SocketTimeout
        clientHttpRequestFactory.setReadTimeout(httpClientPoolConfig.getReadTimeout());
        // ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        clientHttpRequestFactory.setConnectionRequestTimeout(httpClientPoolConfig.getRequestTimeout());
        return clientHttpRequestFactory;
    }


    /**
     * ??????httpClient
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        try {
            //????????????ssl??????
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();
            httpClientBuilder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    // ??????http???https??????
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();
            //??????Httpclient????????????????????????(??????)???????????????netty???okHttp????????????http??????
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // ???????????????
            poolingHttpClientConnectionManager.setMaxTotal(httpClientPoolConfig.getMaxTotal());
            // ??????????????????
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpClientPoolConfig.getMaxPerRoute());
            //???????????????
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
            // ????????????
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(httpClientPoolConfig.getRetryTimes(), true));

            //???????????????????????????
            httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());
            //????????????????????????????????????
            httpClientBuilder.evictExpiredConnections();
            httpClientBuilder.evictIdleConnections(httpClientPoolConfig.getMaxIdleTime(), TimeUnit.MILLISECONDS);
            return httpClientBuilder.build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            log.error("?????????HTTP???????????????", e);
        }
        return null;
    }


    /**
     * ???????????????????????????
     * @return
     */
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy(){
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
//                log.info("HeaderElement:{}", JSON.toJSONString(he));
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch(NumberFormatException ignore) {
                        log.error("?????????????????????????????????",ignore);
                    }
                }
            }
            //???????????????????????????????????????
            return 60000l;
        };
    }

//    /**
//     * ???????????????
//     *
//     * @return
//     */
//    private List<Header> getDefaultHeaders() {
//        List<Header> headers = new ArrayList<>();
//        headers.add(new BasicHeader("User-Agent",
//                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
//        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
//        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
//        headers.add(new BasicHeader("Connection", "Keep-Alive"));
//        return headers;
//    }

    /**
     * ?????????????????????????????????utf-8
     *
     * @param restTemplate
     */
    private void modifyDefaultCharset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        Charset defaultCharset = Charset.forName("UTF-8");
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));
    }
}
