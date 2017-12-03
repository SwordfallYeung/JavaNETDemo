package b_class;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author SwordFall
 * @create 2017-12-03 16:35.
 * @desc
 *
 * 直接子类为Inet4Address和Inet6Address
 *
 * InetAddress类用来封装数字式的IP地址和该地址的域名
 *
 * InetAddress类中工厂方法:
 * InetAddress类没有明显的构造函数.为生成一个InetAddress对象,必须运用一个可用的工厂方法.
 *
 * 工厂方法(factory method) 仅是一个类中静态方法返回一个该类实例的约定.
 * 对于InetAddress,三个方法:getLocalHost(),getByName()以及getAllByName()可以用来创建InetAddress的实例.
 *
 * getLocalHost()仅返回象征本地主机的InetAddress对象.
 *       本机地址为localhost, 127.0.0.1
 *
 * getByName()方法返回一个传给它的主机名的InetAddress.
 *
 * getAllByName()工厂方法返回代表由一个特殊名称分解的所有地址的InetAddress类数组
 *
 *
 * InetAddress的构造函数不是公开的(public),所以需要通过它提供的静态方法来获取,有一下的方法:
 * static InetAddress[] getAllByName(String host)
 * static InetAddress getByAddress(byte[] addr)
 * static InetAddress getByAddress(String host,byte[] addr)
 * static InetAddress getByName(String host)
 * static InetAddress getLocalHost()
 *
 *
 *
 **/
public class InetAddressDemo {

    public static void main(String[] args) {
        /*getByName();*/
        /*test();*/
        getLocalhost();
    }

    public static void getByName(){
        try {
            InetAddress address=InetAddress.getByName("www.baidu.com");
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void test(){
        try {
            InetAddress address=InetAddress.getByName("www.google.com");
            System.out.println(address);
            System.out.println("------");

            InetAddress[] addresses=InetAddress.getAllByName("www.google.com");
            for (InetAddress addr:addresses){
                System.out.println(addr);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void getLocalhost(){
        try {
            InetAddress address=InetAddress.getLocalHost();
            System.out.println(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
