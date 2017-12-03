package b_class;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author SwordFall
 * @create 2017-12-03 15:04.
 * @desc
 *
 * 抽象类
 *
 * 直接子类为HttpsURLConnection, 父类为URLConnection
 *
 *
 * 使用jdk自带的HttpURLConnection向URL发送POST请求并输出响应结果
 * 参数使用流传递，并且硬编码为字符串"name=XXX"的格式
 *
 **/
public class HttpURLConnectionDemo {
    public static void main(String[] args) throws Exception{
        String urlPath=new String("http://www.baidu.com");
        String param="name"+ URLEncoder.encode("丁丁","UTF-8");
        //建立连接
        URL url=new URL(urlPath);
        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        //设置参数
        httpURLConnection.setDoOutput(true);//需要输出
        httpURLConnection.setDoInput(true);//需要输入
        httpURLConnection.setUseCaches(false);//不允许缓存
        httpURLConnection.setRequestMethod("POST");//设置POST方式连接
        //设置请求属性
        httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpURLConnection.setRequestProperty("Connection","Keep-Alive");//维持长连接
        httpURLConnection.setRequestProperty("Charset","UTF-8");
        //连接,也可以不用明文connect,使用下面的httpURLConnection.getOutputStream()会自动connect
        httpURLConnection.connect();
        //建立输入流,向指定的URL传入参数
        DataOutputStream dos=new DataOutputStream(httpURLConnection.getOutputStream());
        dos.writeBytes(param);
        dos.flush();
        dos.close();
        //获得响应状态
        int resultCode=httpURLConnection.getResponseCode();
        if (HttpURLConnection.HTTP_OK==resultCode){
            StringBuffer sb=new StringBuffer();
            String readLine=new String();
            BufferedReader responseReadeer=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
            while ((readLine=responseReadeer.readLine())!=null){
                sb.append(readLine).append("\n");
            }
            responseReadeer.close();
            System.out.println(sb.toString());
        }
    }
}
