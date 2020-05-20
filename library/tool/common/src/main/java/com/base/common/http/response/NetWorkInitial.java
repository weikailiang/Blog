package com.base.common.http.response;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by weikailiang on 2020/5/6.
 */

public class NetWorkInitial {
    static HashMap<String, String> sHeaders;
    public static void setHeaders(HashMap<String, String> headers) {
        // TODO: 2019/4/9 如有需要调用这个方法添加请求头
        sHeaders = headers;
    }


    public static void addCustom(final OkHttpClient.Builder builder) {
        builder.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (sHeaders != null && !sHeaders.isEmpty()) {
                    Request.Builder builder_request = request.newBuilder();
                    Set<Map.Entry<String, String>> entries = sHeaders.entrySet();
                    Iterator<Map.Entry<String, String>> iterator = entries.iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> next = iterator.next();
                        builder_request.addHeader(next.getKey(), next.getValue());
                    }
                    request = builder_request.build();
                }

                return chain.proceed(request);
            }
        });


    }


    public static void setCertificates(OkHttpClient.Builder builder) {
        // TODO: 2019/4/9 如有需要在这里添加证书

        //
        String cer = "";//证书字符串

        if(cer.isEmpty())
        {
            return;
        }
        //
        try {

            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;//是否过滤主机名，true不过滤
                }
            });


            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            Buffer buffer = new Buffer().writeUtf8(cer);
            InputStream inputStream = buffer.inputStream();
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(inputStream));

            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );

            builder.sslSocketFactory(sslContext.getSocketFactory());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String customDecode(retrofit2.Response<String> response)
    {
        // TODO: 2019/4/9 add custom 如有需要在这里添加自定义响应体处理
        return response.body();
    }
}
