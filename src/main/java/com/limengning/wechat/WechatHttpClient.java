package com.limengning.wechat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

public class WechatHttpClient {
    private final String baseApi = "https://api.weixin.qq.com/cgi-bin/";

    public WechatHttpClient() {
    }

    public <T> T get(String url, Map<String, String> parameters) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri;
            try {
                var uriBuilder = new URIBuilder(baseApi + url);
                if (parameters != null) {
                    for (Entry<String, String> entry : parameters.entrySet()) {
                        uriBuilder.addParameter(entry.getKey(), entry.getValue());
                    }
                }
                uri = uriBuilder.build();
            } catch (URISyntaxException ex) {
                return null;
            }
            HttpGet httpGet = new HttpGet(uri);
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                String json = EntityUtils.toString(response.getEntity());
                Json
            }
        }

    }
}
