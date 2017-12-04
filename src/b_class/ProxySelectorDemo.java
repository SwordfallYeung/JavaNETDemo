package b_class;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
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
 *
 * 3.ProxySelector-----自动代理选择器:
 *   1)上面直接显示传入Proxy对象.
 *   2)Java提供了一个抽象类ProxySelector,该类的对象可以根据你要连接的URL自动选择最合适的代理.
 *   3)选择器实现完毕后就可以创建选择器的对象实例
 *   4)ProxySelector:其中有两个重要的方法需要实现
 *      i.List<Proxy> select(URI uri)
 *      ii.void connectFailed(URI uri,SocketAddress sa, IOException ioe)
 *
 **/
public class ProxySelectorDemo {

    private final String PROXY_ADDR="139.82.12.188";
    private final int PROXY_PORT=3124;

    public void init(){
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {//默认任何URI都返回一个代理
                List<Proxy> list=new ArrayList<>();
                list.add(new Proxy(Proxy.Type.HTTP,new InetSocketAddress(PROXY_ADDR,PROXY_PORT)));
                return list;
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {//简单地打印信息
                System.out.println("无法连接到代理!");
            }
        });
        //正常的访问网络
    }

    public static void main(String[] args) {
        new ProxySelectorDemo().init();
    }
}

