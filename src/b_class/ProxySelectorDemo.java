package b_class;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author SwordFall
 * @create 2017-12-04 20:35.
 * @desc
 *
 * 抽象类
 *
 * 1.代理服务器:
 *   1)英文就叫Proxy,即代替用户去连接想要的网站并获取信息;
 *   2)一般主流的商业浏览器都提供代理的功能,即你可以先设置一个代理服务器,那么接下来所有的上网都是通过代理服务器完成;
 *   3)一旦设置好了代理服务器,那么你之后所有的上网行为都是通过代理服务器完成的;
 *   4)为什么需要代理服务器:
 *      i:处于特殊安全原因需要对外隐藏自己的IP,代理服务器对外只能暴露代理服务器本身的ip,但是可以隐藏你的IP;
 *      ii:代理服务器可以提供缓存,大大提高访问速度
 *
 * 2.Java对代理服务器的支持:
 *   1) Java中就用proxy类来代理代理服务器.
 *   2) 在URL的openConnection就有一个版本,其参数就是Proxy对象,包括Socket等的构造器也都有传入Proxy对象的重载版本.
 *   3) Proxy构造器:Proxy(Proxy.Type type, SocketAddress sa);
 *   4) openConnection时指定代理:URLConnection URL.openConnection(Proxy proxy);//用指定的代理服务器代开连接
 *   5) 指定socket使用代理服务器:Socket(Proxy proxy);
 **/
public class ProxySelectorDemo {

    //代理服务器的IP和端口
    private final String PROXY_ADDR="129.82.12.188";
    private final int PROXY_PORT=3124;

    public void init() throws Exception{
        URL url=new URL("http:www.baidu.com");//想访问的网址
        Proxy proxy=new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT));
        URLConnection urlConnection=url.openConnection(proxy);//连接时设置代理
        urlConnection.setConnectTimeout(3000);

        Scanner scanner=new Scanner(urlConnection.getInputStream());
        PrintStream ps=new PrintStream("index.htm");
        while (scanner.hasNextLine()){
            //直接将返回的网页代码下载到本地的index.htm文件中
            String line=scanner.nextLine();
            System.out.println(line);
            ps.println(line);
        }
    }

    public static void main(String[] args) throws Exception {
            new ProxySelectorDemo().init();
    }
}

