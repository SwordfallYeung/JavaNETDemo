package b_class;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author y15079
 * @create 2017-12-12 19:45
 * @desc
 *
 * 抽象类
 * 子类为InetSocketAddress
 *
 * 用于获取一些基本的网址信息
 **/
public class SocketAddressDemo {
	public static void main(String[] args) throws Exception{
		Socket socket1=new Socket("www.baidu.com",80);
		SocketAddress socketAddress=socket1.getRemoteSocketAddress();
		socket1.close();

		InetSocketAddress inetSocketAddress1=(InetSocketAddress)socketAddress;
		System.out.println("服务器域名："+inetSocketAddress1.getAddress().getHostName());
		System.out.println("服务器IP:"+inetSocketAddress1.getAddress().getHostAddress());
		System.out.println("服务器端口："+inetSocketAddress1.getPort());

		///////////////////////////////////////////////////////////////////////////////

		Socket socket2=new Socket();
		socket2.connect(socketAddress);
		socket2.close();
		InetSocketAddress inetSocketAddress2=(InetSocketAddress)socket2.getLocalSocketAddress();
		System.out.println("本地IP："+inetSocketAddress2.getAddress().getLocalHost().getHostAddress());
		System.out.println("本地端口："+inetSocketAddress2.getPort());
	}
}
