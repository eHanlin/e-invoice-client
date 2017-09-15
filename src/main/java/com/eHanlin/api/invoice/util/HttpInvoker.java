package com.eHanlin.api.invoice.util;

import okhttp3.*;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Map;

public class HttpInvoker {

    private OkHttpClient client;

    public HttpInvoker() {
        client = buildTrustAllOkHttpClient();
    }

    public String post(String url, Map<String, String> body) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        body.forEach(formBodyBuilder::add);
        return send(new Request.Builder()
                            .url(url)
                            .post(formBodyBuilder.build())
                            .build());
    }

    private String send(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return "{\"error\": \"Response fail.\"}";
            }

        } catch (IOException e) {
            return "{\"error\": \"IO exception.\"}";
        }
    }

    /**
     * 使 OK HTTP client 忽略 TLS 信任錯誤
     */
    private OkHttpClient buildTrustAllOkHttpClient() {
        final TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {}

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            }
        };

        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, null);
        } catch (Exception e) {
            throw new RuntimeException("OKHttp is not OK!");
        }

        return new OkHttpClient.Builder()
            .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
            .hostnameVerifier((String s, SSLSession sslSession) -> true)
            .build();
    }

}

