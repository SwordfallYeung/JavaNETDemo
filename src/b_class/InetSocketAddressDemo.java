package b_class;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * @author SwordFall
 * @create 2017-12-03 17:11.
 * @desc
 *
 * 父类为SocketAddress
 *
 * InetSocketAddress是SocketAddress的实现子类
 * 此类实现IP套接字地址(IP地址+端口号),不依赖任何协议.
 * 在使用Socket来连接服务器时最简单的方式就是直接使用IP和端口,但是Socket类中的connect方法并未提供这种方式,
 * 而是使用SocketAddress类来向connect方法传递服务器的IP和端口.
 * SocketAddress只是个抽象类,它除了有一个默认的构造方法外,其他的方法都是abstract的,因此,我们必须使用SocketAddress的子类来建立SocketAddress对象,也就是唯一的子类InetSocketAddress
 *
 *
 * InetAddress与InetSocketAddress的区别:
 * 关键就是InetSocketAddress不基于任何协议,一般用于socket编程中.
 * 表面看InetSocketAddress多了一个端口号,端口的作用:一台拥有IP地址的主机可以提供多服务,比如web服务,FTP服务,SMTP服务等,这些服务完全可以通过1个IP地址来实现.
 *
 * 主机是怎么区分不同的网络服务的,实际上是通过"IP地址+端口号"来区分不同的服务的.
 **/
public class InetSocketAddressDemo {
    public static void main(String[] args) {
        useInetAddress();
        useInetSocketAddress();
    }

    public static void useInetAddress(){
        try {
            InetAddress address=InetAddress.getLocalHost();
            System.out.println("主机名: "+address.getHostName()+", ip地址:"+address.getHostAddress());
            InetAddress add=InetAddress.getByName("www.baidu.com");
            System.out.println(add.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void useInetSocketAddress(){
        final Socket socket=new Socket();
        SocketAddress address=new InetSocketAddress("www.fortify.net",443);
        try {
            socket.connect(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread reader=new Thread(){
            @Override
            public void run() {
                try {
                    byte[] buffer=new byte[512];
                    InputStream stream=socket.getInputStream();
                    socket.getInputStream().read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        reader.start();
    }
}
