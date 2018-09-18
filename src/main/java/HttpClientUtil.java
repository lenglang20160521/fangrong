import DTO.*;
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


//获取权限范围内可见电站
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
        Points points = getStationAnaPoints(stationId, token);
        return points.getPointIds();
    }

    public static List<String> getAnaPointNames(Integer stationId, String token) {
        Points points = getStationAnaPoints(stationId, token);
        return points.getPointNames();
    }

    public static Map<Integer, String> getIdNameMaps(Integer stationId, String token) {
        Points points = getStationAnaPoints(stationId, token);
        return points.getIdNameMaps();
    }

    public static Map<Integer, String> getIdShortNameMaps(Integer stationId, String token) {
        Points points = getStationAnaPoints(stationId, token);
        return points.getIdShortNameMaps();
    }

    public static Map<Integer, String> getIdAliasNameMaps(Integer stationId, String token) {
        Points points = getStationAnaPoints(stationId, token);
        return points.getIdAliasNameMaps();
    }

//    获取电站下遥测量测点列表
    public static Points getStationAnaPoints(Integer stationId, String token) {
        String getAnaPointsUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/ana";
        return getPoints(getAnaPointsUrl,token);
    }
    //    获取电站下电度量测点列表
    public static Points getStationAccPoints(Integer stationId, String token) {
        String getAnaPointsUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/acc";
        return getPoints(getAnaPointsUrl,token);
    }

    //获取电站下断路器列表
    public  static Breakers getStationBreakers(Integer stationId, String token){
        String getStationBreakersUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/breakers";
        return getBreakers(getStationBreakersUrl,token);
    }
    //获取断路器下电度量测点列表
    public static Points getBreakersAccPoints(Integer stationId, Integer beakerId,String token){
        String getBreakersAccPointsUrl =   "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/breakers/"+beakerId.toString()+"/acc";
        return getPoints(getBreakersAccPointsUrl,token);
    }
    //获取断路器下遥测量测点列表
    public static Points getBreakersAnaPoints(Integer stationId, Integer beakerId,String token){
        String getBreakersAnaPointsUrl =   "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/breakers/"+beakerId.toString()+"/ana";
        return getPoints(getBreakersAnaPointsUrl,token);
    }

    //获取电站下回路列表
    public  static Circuits getStationCircuits(Integer stationId, String token){
        String getStationCircuitsUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/circuits";
        return getCircuits(getStationCircuitsUrl,token);
    }
    //    获取回路下断路器列表
    public  static Breakers getCircuitBreakers(Integer stationId, Integer circuitId, String token){
        String getCircuitBreakersUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/circuits/"+circuitId.toString()+"/breakers";
        return getBreakers(getCircuitBreakersUrl,token);
    }

//    获取回路下子回路列表
public  static Circuits getCircuitCircuits(Integer stationId,Integer circuitId, String token){
    String getCircuitCircuitsUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/circuits/"+circuitId.toString()+"/circuits";
    return getCircuits(getCircuitCircuitsUrl,token);
}
//获取回路下变压器列表
public  static Transformers getCircuitTransformers(Integer stationId,Integer circuitId, String token) {
    String getCircuitTransformersUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/circuits/" + circuitId.toString() + "/transformers";
    return getTransformers(getCircuitTransformersUrl, token);
}

//获取电站下变压器列表
public  static Transformers getStationTransformers(Integer stationId, String token){
    String getStationTransformersUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/transformers";
    return getTransformers(getStationTransformersUrl,token);
}

//    获取变压器下电度量测点列表
public static Points getTransformerAccPoints(Integer stationId, Integer transformerId,String token){
    String getTransformerAccPointsUrl =   "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/transformers/"+transformerId.toString()+"/acc";
    return getPoints(getTransformerAccPointsUrl,token);
}

    //    获取变压器下遥测量测点列表
    public static Points getTransformerAnaPoints(Integer stationId, Integer transformerId,String token){
        String getTransformerAnaPointsUrl =   "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/transformers/"+transformerId.toString()+"/ana";
        return getPoints(getTransformerAnaPointsUrl,token);
    }



    public static Circuits getCircuits(String url,String token){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        return JSON.parseObject(doGet(url, headers), Circuits.class);
    }

    public  static Transformers getTransformers(String url,String token){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        return JSON.parseObject(doGet(url, headers), Transformers.class);
    }
    public static Breakers getBreakers(String url,String token){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        return JSON.parseObject(doGet(url, headers), Breakers.class);
    }

    public static Points getPoints(String url,String token){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", token);
        return JSON.parseObject(doGet(url, headers), Points.class);
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


//    申请访问token
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

    public  static  void  main(String[] args){
        String newToken = HttpClientUtil.getNewToken();
        Integer stationId = 2140000003;
        String getStationTransformersUrl = "http://www.ie-cloud.com:18686/api/v2/stations/" + stationId.toString() + "/transformers";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-ie-access-key", FangRongConfig.x_ie_access_key);
        headers.put("x-ie-access-token", newToken);
//        JSON.parseObject(doGet(url, headers), Transformers.class)
        String jsonStr = doGet(getStationTransformersUrl, headers);
//        System.out.println(jsonStr);
        Transformers transformers = JSON.parseObject(jsonStr,Transformers.class);
        System.out.println(transformers.getTransformers().size());
        List<String> a = new ArrayList<>();
        System.out.println(a);
//        if (transformers.getData().equals("[]")){
//            System.out.println("dddd");
//        }
//        for (DTO.Transformers.DataBean transformer: transformers.getData()){
//            if (transformer == null){
//                System.out.println("无数据");
//            }else {
//                System.out.println("????");
//            }
//        }
//        Transformers transformers = HttpClientUtil.getStationTransformers(stationId,newToken);
//        for (DTO.Transformers.DataBean transformer: transformers.getData()){
//            System.out.println(transformer.toString());
//        }
    }
}


