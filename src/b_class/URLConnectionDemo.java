package b_class;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author SwordFall
 * @create 2017-12-22 00:01.
 * @desc
 *
 * 为抽象类,表示指向URL指定资源的活动连接
 * URLConnection类本身依赖于Socket类实现网络连接.一般认为,URLConnection类提供了比Socket类更易于使用/更高级的网络连接抽象.URLConnection贴近HTTP协议.
 *
 * URLConnection, 子类为HttpURLConnection,JarURLConnection
 *
 * 构造函数
 * protected URLConnection(URL url) 　构造一个到指定 URL 的 URL 连接。
 *
 * connect()方法由子类实现本地与服务器的连接方式。一般使用TCP socket，但也可以使用其他其他机制来建立。
 * abstract  void connect()   打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
 *
 * 读取首部:
 * String getContentType()  返回 Content-type 头字段的值。即数据的MIME内容类型。
 * int getContentLength()   返回 Content-length 头字段的值。该方法获取内容的字节数。
 * String getContentEncoding()   返回 Content-encoding 头字段的值。获取内容的编码方式。
 * long getDate()  返回 date 头字段的值。获取请求的发送时间。
 * long getExpiration()  　返回 expires 头字段的值。获取Expires的值。
 * long getLastModified()  返回 last-modified 头字段的值。
 *
 * https://www.cnblogs.com/shijiaqi1066/p/3753224.html
 **/
public class URLConnectionDemo {
    public static void main(String[] args) {

    }

    //获取URLConnection实体类
    public void getUrlConnection() throws Exception{
        URL url=new URL("http://www.baidu.com");
        URLConnection uc=url.openConnection();
    }

    //使用URLConnection请求Baidu首页
    //该程序请求方法为get。使用getInputStream()方法输出的内容只有响应体，不包含响应的首部。
    public void testGetInputStream() throws Exception{
        InputStream in = null;

        //连接
        URL url=new URL("http://www.baidu.com");
        URLConnection uc=url.openConnection();

        //获取读入流
        in=uc.getInputStream();

        //放入缓存流
        InputStream raw=new BufferedInputStream(in);
        //最后使用Reader接收
        Reader r=new InputStreamReader(raw);

        //打印输出
        int c;
        while ((c=r.read())>0){
            System.out.print((char)c);
        }

        if(in!=null){
            in.close();
        }
    }

    //获取HttpURLConnection对象
    public void testHttpURLConnection(){
        try {
            URL url = new URL("http:www.baidu.com");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
