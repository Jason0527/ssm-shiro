package com.jeason.common.utils;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jason
 */
public class HttpUtils {
    public static String doHttpRequest(String commonUrl, String requestMethod, int timeout, String msg, String charset){
        String reMsg = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(commonUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(requestMethod);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("User-Agent","MSIE");
            urlConnection.setConnectTimeout(timeout);
            urlConnection.setReadTimeout(timeout);
            urlConnection.connect();
            /*发�?�信�?*/
            if (msg != null && msg.trim().length() != 0){
                if (charset == null){
                    urlConnection.getOutputStream().write(msg.getBytes());
                }else{
                    urlConnection.getOutputStream().write(msg.getBytes(charset));
                }
            }
            int resCode = urlConnection.getResponseCode();
            if (resCode != 200){
                /*通信异常*/

            }else {
                BufferedInputStream bs = new BufferedInputStream(urlConnection.getInputStream());
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte [] buff = new byte['E'];
                int len = 0;
                int contentLength = urlConnection.getContentLength();
                if (contentLength == -1){
                    /*长度未知，可能返回多个�?�信包，�?要多次读�?*/
                    while ((len = bs.read(buff)) != -1){
                        buffer.write(buff,0,len);
                    }
                } else {
                    int readLen = 0;//已读取长�?
                    while ((len = bs.read(buff)) != -1){
                        buffer.write(buff,0,len);
                        readLen += len;
                        if (readLen >= contentLength) {
                            break;
                        }
                    }
                }
                if (charset == null) {
                    reMsg = new String(buffer.toByteArray());
                }else {
                    reMsg = new String(buffer.toByteArray(),charset);
                }
            }
            return reMsg;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null){
                try {
                    urlConnection.disconnect();
                    urlConnection = null;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return reMsg;
    }







}
