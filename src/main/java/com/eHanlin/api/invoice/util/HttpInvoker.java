package com.eHanlin.api.invoice.util;

import okhttp3.*;

import javax.net.ssl.*;
import java.util.Map;

public class HttpInvoker {

    private final OkHttpClient client;

    public HttpInvoker() {
        client = buildTrustAllOkHttpClient();
    }

    public String post(String url, Map<String, String> body) throws Exception {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        body.forEach(formBodyBuilder::add);
        return send(new Request.Builder()
                            .url(url)
                            .post(formBodyBuilder.build())
                            .build());
    }

    private String send(Request request) throws Exception {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                StringBuilder headers = new StringBuilder();
                response.headers().toMultimap().forEach((name, values) -> headers.append(name).append("=").append(values).append("\n"));
                throw new RuntimeException(headers.toString());
            }
        }
    }

    /**
     * 使 OK HTTP client 忽略 TLS 信任錯誤
     */
    private OkHttpClient buildTrustAllOkHttpClient() {
        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        };

        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { x509TrustManager }, null);
        } catch (Exception e) {
            throw new RuntimeException("OKHttp is not OK!");
        }

        return new OkHttpClient.Builder()
            .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
            .hostnameVerifier((String s, SSLSession sslSession) -> true)
            .build();
    }

}

