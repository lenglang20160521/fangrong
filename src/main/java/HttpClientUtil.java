import DTO.AuthInfo;
import DTO.Points;
import DTO.Stations;
import DTO.resultPoints;
import com.alibaba.fastjson.JSON;
import config.FangRongConfig;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpClientUtil {

    public static String doPost(String url, String jsonstr, Map<String, String> headersMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);

        // 设置请求头
        if (null != headersMap && headersMap.size() > 0) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        httpPost.addHeader("Content-Type", "application/json");
        if (null != jsonstr) {
            StringEntity se = null;
            try {
                se = new StringEntity(jsonstr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            httpPost.setEntity(se);
        }

        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String doGet(String url, Map<String, String> headersMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(35000)// 请求超时时间
                    .setSocketTimeout(60000)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);

            // 设置请求头
            if (null != headersMap && headersMap.size() > 0) {
                for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

//    public static List<Integer> getStationIds(String token) {
//        String getStationsUrl = "http://www.ie-cloud.com:18686/api/v2/stations";
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
//        headers.put("x-ie-access-token", token);
//        Stations stations = JSON.parseObject(doGet(getStationsUrl, headers), Stations.class);
//        return stations.getStationIds();
//    }

    public static Stations getStations(String token){
        String getStationsUrl = "http://www.ie-cloud.com:18686/api/v2/stations";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        String resultJson = doGet(getStationsUrl, headers);
//        System.out.println(resultJson);
        return JSON.parseObject(resultJson, Stations.class);
    }


    public static List<Integer> getAnaPointIds(Integer stationId, String token) {
        Points points = getStationPoints(stationId, token);
        return points.getPointIds();
    }

    public static List<String> getAnaPointNames(Integer stationId, String token) {
        Points points = getStationPoints(stationId, token);
        return points.getPointNames();
    }

    public static Map<Integer, String> getIdNameMaps(Integer stationId, String token) {
        Points points = getStationPoints(stationId, token);
        return points.getIdNameMaps();
    }

    public static Map<Integer, String> getIdShortNameMaps(Integer stationId, String token) {
        Points points = getStationPoints(stationId, token);
        return points.getIdShortNameMaps();
    }

    public static Map<Integer, String> getIdAliasNameMaps(Integer stationId, String token) {
        Points points = getStationPoints(stationId, token);
        return points.getIdAliasNameMaps();
    }

    public static Points getStationPoints(Integer stationId, String token) {
        String getAnaPointsUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/ana";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        return JSON.parseObject(doGet(getAnaPointsUrl, headers), Points.class);
    }

    public static resultPoints getResultPoints(Integer stationId, String token) {
        String queryByPointsUrl = "http://www.ie-cloud.com:18686/api/v2/hsda/stations/" + stationId.toString() + "/ana";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        List<Integer> ids = getAnaPointIds(stationId, token);
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        String str = JSON.toJSONString(map);
        String newResult = doPost(queryByPointsUrl, str, headers);
        return JSON.parseObject(newResult, resultPoints.class);
    }


    public static String getNewToken() {
        String reqBaseUrl = "http://www.ie-cloud.com:18686/api/v2/auth";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", FangRongConfig.x_ie_access_token);
//        Map<String, Object> paramMap = null;
        String jsonStr = null;
        String result = doPost(reqBaseUrl, jsonStr, headers);
        AuthInfo authInfo = JSON.parseObject(result, AuthInfo.class);
        return authInfo.getData().getAccessToken();
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(Integer s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}


