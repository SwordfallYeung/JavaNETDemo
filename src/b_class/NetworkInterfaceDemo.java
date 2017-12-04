package b_class;

import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author y15079
 * @create 2017-12-04 15:15
 * @desc
 *
 * final 类
 *
 * java 通过NetworkInterface获取本机ip地址信息
 *
 * socket:一台机器的通讯节点称为socket，在java中，socket是java.net包里面的
 * Socket，ServerSocket，DatagramSocket，MultiSocket的实例
 *
 * IP地址java中用java.net.InetAddress表示
 * InetAddress: ip地址或者域名，指向远程地址。如果域名解析不了，则出错。
 *             抽象类，有两个子类：Inet4Address和Inet6Address。这两个类只能通过InetAddress的静态方法获取，不能直接构造。
 * InetSocketAddress extends SocketAddress：ip socket地址，用｛ip, port｝或者｛hostname，port｝表示。也能单独用port构造，表示本机ip地址，所有本机网络地址。
 * NetwordInterface: 本机网络接口，由多个网络接口名称和对应的网络接口的ip地址列表构成。
 *
 * loopback: 127.0.0.1 用来检测本机的host名称，或者检测本地ip地址是否可用
 * wildcard: 0.0.0.0 绑定了本机所有的ip地址
 *
 **/
public class NetworkInterfaceDemo {

	public static void main(String[] args) {
			printIp();
	}

	private static final void printIp(){
		try {
			for (Enumeration<NetworkInterface> e=NetworkInterface.getNetworkInterfaces();e.hasMoreElements();){
				NetworkInterface item=e.nextElement();

				System.out.println(item.toString());
				System.out.println(item.getMTU()+" "+item.isLoopback()+" "+item.isPointToPoint()+" "+item.isUp()+" "+item.isVirtual());

				for (InterfaceAddress address:item.getInterfaceAddresses()){
					if (address.getAddress() instanceof Inet4Address){
						Inet4Address inet4Address=(Inet4Address) address.getAddress();
						System.out.println(inet4Address.getHostAddress());
						System.out.println(inet4Address.isLinkLocalAddress()+" "+inet4Address.isLoopbackAddress()+" "+inet4Address.isMCGlobal()+" "+inet4Address.isMulticastAddress());
					}
				}

				System.out.println("-----------------------------------------");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
