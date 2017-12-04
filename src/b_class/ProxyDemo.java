package b_class;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author y15079
 * @create 2017-12-04 17:04
 * @desc
 *
 * 该类代表一个代理设置，典型的类型（http，socks）和一个socket地址。一个代理是不可变的对象。
 *
 *  Proxy(Proxy.Type type ，SocketAddress sa)
 *  address() 返回代理socket地址.如果它是一个直连接则空
 *  type()  返回代理类型
 *
 *  Proxy(代理连接)
 **/
public class ProxyDemo {


	//代理服务器的IP和端口
	private final String PROXY_ADDR="129.82.12.188";
	private final int PROXY_PORT=3124;

	public void init() throws Exception{
		URL url=new URL("http://www.baidu.com");//想访问的网址
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
		new ProxyDemo().init();
	}

}
