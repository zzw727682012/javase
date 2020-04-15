package com.https;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Test {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, InvalidKeyException {

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new Test.TrustAnyTrustManager() },
                new java.security.SecureRandom());
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.100.2", 8080));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "https://partner.uat.shopeemobile.com/redirect/airway_bill/sg?token=T8vpujPUnNsgr7buCeHx7ViqBJCoRvtP8AW/JlQLEbUHMj5U0aT0AjssNFaRmc5s257khaECpvykG%2BG09bl58A==";
        HttpGet get = new HttpGet(url);
        HttpHost proxy = new HttpHost("172.16.100.2",8080);
        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).build();
        get.setConfig(requestConfig);


        CloseableHttpResponse res = httpclient.execute(get);
        if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            HttpEntity entity = res.getEntity();// 返回json格式：
            String path = "/FTP/FILA/SHOPEE";
            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            File file1 = new File(path+"\\123.txt");
            file1.createNewFile();
            String path1111 = file.getPath() +"/"+ "bbb"+".pdf";
            System.out.println(path1111);

            OutputStream outputStream = new FileOutputStream(path1111);
            entity.writeTo(outputStream);
            outputStream.close();
        }
    }
    private static  class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }

    }
}
