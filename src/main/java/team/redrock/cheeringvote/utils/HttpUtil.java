package team.redrock.cheeringvote.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import team.redrock.cheeringvote.exception.ValidException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

/**
 * @author 陌花采撷
 */
@Component
public class HttpUtil {

    public  JSONObject httpRequestToString(String path, String method, String body) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (path == null || method == null) {
            return null;
        }

        JSONObject jsonObject = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        HttpsURLConnection conn = null;

        try {
            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(method);

            if (null != body) {
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(body.getBytes("UTF-8"));
                    outputStream.close();

            }
            // 将返回的输入流转换成字符串
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

             jsonObject = JSON.parseObject(buffer.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                bufferedReader.close();
                inputStreamReader.close();
                inputStream.close();
            } catch (IOException execption) {
                execption.printStackTrace();
            }

        }
        if(jsonObject==null){
            try {
                throw new ValidException("Fail to authorize");
            } catch (ValidException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;

    }


}
