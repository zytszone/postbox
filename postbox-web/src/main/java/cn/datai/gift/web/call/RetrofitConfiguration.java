package cn.datai.gift.web.call;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * Created by js on 2016/9/26 checkdown.
 */
@Configuration
public class RetrofitConfiguration {

    private boolean skipVerify = true;

    @Value("${weixin.pay.keypath}")
    private String WX_KEY_PATH;

    @Value("${weixin.pay.key_store_type}")
    private String WX_KEY_STORE_TYPE;

    @Value("${weixin.pay.key_store_pwd}")
    private String WX_KEY_STORE_PASSWORD;


    @Bean
    public OkHttpClient okHttpClient() {
        Logger logger = LoggerFactory.getLogger("OkHttpLogging");

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger::debug);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        if (skipVerify) {
            client.sslSocketFactory(getUnSafeSSLContext().getSocketFactory(),getUnSafeX509TrustManager());
            client.hostnameVerifier((s, sslSession) -> true);
        }

        return client.build();
    }

    /**
     * xml类型请求client
     * @return
     */
    @Bean
    public OkHttpClient xmlOkHttpClient() throws FileNotFoundException, KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyManagementException {
        Logger logger = LoggerFactory.getLogger("OkHttpLogging");

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);

        client.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/xml; charset=UTF-8")
                    .header("Connection","close");
            return chain.proceed(requestBuilder.build());
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger::debug);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        if (false) {
            client.sslSocketFactory(getUnSafeSSLContext().getSocketFactory(),getUnSafeX509TrustManager());
            client.hostnameVerifier((s, sslSession) -> true);
        }
        return client.build();
    }


    /**
     * xml类型请求client, 微信自签双向认证
     * @return
     */
    @Bean
    public OkHttpClient xmlSSLOkHttpClient() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, NoSuchProviderException, KeyManagementException {
        Logger logger = LoggerFactory.getLogger("OkHttpLogging");

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);

        client.networkInterceptors().add(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/xml; charset=UTF-8")
                    .header("Connection","close");
            return chain.proceed(requestBuilder.build());
        });

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(logger::debug);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        if (StringUtils.isNotBlank(WX_KEY_PATH)) {
            // 实例化密钥库
            KeyStore ks = KeyStore.getInstance(WX_KEY_STORE_TYPE);
            // 获得密钥库文件流
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(new File(WX_KEY_PATH));
                // 加载密钥库
                ks.load(fis, WX_KEY_STORE_PASSWORD.toCharArray());
            } catch (IOException e) {
                logger.error("读取秘钥库失败", e);
            } finally {
                if (fis != null) {
                    try {
                        // 关闭密钥库文件流
                        fis.close();
                    } catch (IOException e) {
                        logger.error("关闭秘钥库文件流失败", e);
                    }
                }
            }

            // 实例化密钥库
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            // 初始化密钥工厂
            kmf.init(ks, WX_KEY_STORE_PASSWORD.toCharArray());

            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());
            // 获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            client.sslSocketFactory(ssf);
        }else {
            logger.error("创建双向认证客户端异常， 证书路径未填，客户端使用的是普通连接");
        }
        return client.build();
    }


    private SSLContext getUnSafeSSLContext() {
        final TrustManager[] trustAllCerts = new TrustManager[]{getUnSafeX509TrustManager()};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, null);
            return sslContext;
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    private X509TrustManager getUnSafeX509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {

                return new X509Certificate[0];
            }
        };
    }
}
