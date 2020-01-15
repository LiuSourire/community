package com.sourire.community.provider;

import com.alibaba.fastjson.JSON;
import com.sourire.community.dto.AccessTokenDTO;
import com.sourire.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.sourire.community.provider.TrustAllCerts.createSSLSocketFactory;

@Component
public class GithubProvider {
    private static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient.Builder builder = new OkHttpClient.Builder();

    static {
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.sslSocketFactory(createSSLSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    private OkHttpClient client = builder.build();

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String accessStr = string.split("&")[0].split("=")[1];
            return accessStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            return JSON.parseObject(string,GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
