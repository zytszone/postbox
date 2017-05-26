package cn.datai.gift.web.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

/** 
 *  
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";  
    private static String UTF_8 = "UTF-8";

    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
  
    private static void init() {  
        if (cm == null) {  
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数  
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2  
        }  
    }  
  
    /** 
     * 通过连接池获取HttpClient 
     *  
     * @return 
     */  
    private static CloseableHttpClient getHttpClient() {
        init();  
        return HttpClients.custom().setConnectionManager(cm).build();
    }  
  
    /** 
     *  
     * @param url 
     * @return 
     */  
    public static String httpGetRequest(String url) {  
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);  
    }  
  
    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);  
  
        HttpGet httpGet = new HttpGet(ub.build());
        return getResult(httpGet);  
    }  
  
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)  
            throws URISyntaxException {  
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);  
  
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
        return getResult(httpGet);  
    }  
  
    public static String httpPostRequest(String url) {  
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);  
    }  
  
    public static String httpPostRequest(String url, Map<String, Object> params) throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);  
    }  
  
    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)  
            throws UnsupportedEncodingException {  
        HttpPost httpPost = new HttpPost(url);
  
        for (Map.Entry<String, Object> param : headers.entrySet()) {  
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));  
        }  
  
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
  
        return getResult(httpPost);  
    }  
  
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {  
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }  
  
        return pairs;  
    }  
  
    /** 
     * 处理Http请求 
     *  
     * @param request 
     * @return 
     */  
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {  
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {  
                String result = EntityUtils.toString(entity,UTF_8);
                response.close();  
                return result;
            }  
        } catch (ClientProtocolException e) {
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
  
        }  
  
        return EMPTY_STR;  
    }

    public static String httpPostWithJSON(String url, String json) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        StringEntity se = new StringEntity(json,UTF_8);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        return getResult(httpPost);
    }

    public static String receivePost(HttpServletRequest request) throws IOException {

        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }

        // 将资料解码
        String reqBody = sb.toString();
        return URLDecoder.decode(reqBody, UTF_8);
    }


}  