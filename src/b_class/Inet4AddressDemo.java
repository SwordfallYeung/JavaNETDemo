package b_class;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * @author SwordFall
 * @create 2017-12-03 16:07.
 * @desc
 *
 * final类
 *
 * 父类为InetAddress
 *
 * IPv4么
 *
 * 为了区分IPv4和IPv6地址,Java提供了两个类:Inet4Address和Inet6Address,它们都是InetAddress类的子类
 *
 * 当使用InetAddress类的四个静态方法创建InetAddress对象后，可以通过getAddress返回的byte数组来判断这个IP地址是IPv4还是IPv6地址
 * （byte数组长度为4就是IPv4地址，byte数组长度为16就是IPv6地址），
 * 也可以将instanceof来确定InetAddress对象是它的哪个子类的实例
 *
 *
 **/
public class Inet4AddressDemo {
    public static void main(String[] args) throws Exception{
        /*String website="www.google.com";*/
        String website="www.neu6.edu.cn";

        InetAddress address=InetAddress.getByName(website);
        System.out.println("IP:"+address.getHostAddress());
        switch (address.getAddress().length){
            case 4:
                System.out.println("根据byte数组长度判断这个IP地址是IPv4地址!");
                break;
            case 6:
                System.out.println("根据byte数组长度判断这个IP地址是IPv6地址!");
                break;
        }
        if (address instanceof Inet4Address){
            System.out.println("使用instanceof判断这个IP地址是IPv4地址!");
        }else if (address instanceof Inet6Address){
            System.out.println("使用instanceof判断这个IP地址是IPv6地址!");
        }
    }
}
