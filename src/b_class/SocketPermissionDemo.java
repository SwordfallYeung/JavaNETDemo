package b_class;

import java.net.*;

/**
 * @author SwordFall
 * @create 2017-12-12 23:17.
 * @desc
 *
 * final类
 * 父类为Permission
 *
 * 构造函数:
 * SocketPermission(String host, String action) action有四类:accept,connect,listen,resolve
 **/
public class SocketPermissionDemo {
    public static void main(String[] args) throws Exception{
        InetAddress inetAddress=InetAddress.getByName("www.baidu.com");

        String hostAddress=inetAddress.getHostAddress();

        SocketPermission sp=null;

        if (inetAddress instanceof Inet6Address){
            sp=new SocketPermission("["+hostAddress+"]:80", "connect");
        }else {
            sp=new SocketPermission(hostAddress+":80","connect");
        }

        URL url=new URL("www.baidu.com");
        String host=url.getHost();
        sp=new SocketPermission(host+":"+"80","accept");

        sp=new SocketPermission("puffin.eng.sun.com:7777","connect,accept");


    }
}
