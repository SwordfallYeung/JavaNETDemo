package b_class;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author y15079
 * @create 2017-12-21 19:05
 * @desc Java提供了URL类，每一个URL对象封装了资源标识符和协议处理程序。获得URL对象的途径之一是调用URI的toURL()方法，也可以直接调用URL的构造函数来建立URL对象。
 * <p>
 * URL类有多个构造函数。其中最简单的是URL(String url), 它有一个String类型的参数。如果某个URL没有包含协议处理程序或该URL的协议是未知的，其他的构造函数
 * 会产生一个java.net.MalformedURLException。
 * 下面的代码片断演示了使用URL(String url)建立一个URL对象，该对象封装了一个简单的URL组件和http协议处理程序。
 * URL url = new URL ("http://www.informit.com");
 * <p>
 * 一旦拥有了URL对象，就可以使用getAuthority()、getDefaultPort()、 getFile()、 getHost()、 getPath()、getPort()、 getProtocol()、getQuery()、getRef()、getUserInfo()、getDefaultPort()等方法提取各种组件。
 * 如果URL中没有指定端口，getDefaultPort()方法返回URL对象的协议默认端口。getFile()方法返回路径和查询组件的结合体。getProtocol()方法返回资源的连接类型（例如http、mailto、ftp）。getRef()方法返回URL的片断。
 * 最后，getUserInfo()方法返回Authority的用户信息部分。还可以调用openStream()方法得到java.io.InputStream引用。使用这种引用，可以用面向字节的方式读取资源。
 * <p>
 * <p>
 * <p>
 * http://blog.csdn.net/joe_007/article/details/7939471
 **/
public class URLDemo {

	public static void main(String[] args) throws Exception {
		testURL();
	}

	public static void testURL() throws Exception {
		URL url = new URL("http://www.javajeff.com/articles/articles/html");

		System.out.println("Authority = " + url.getAuthority());
		System.out.println("Default port = " + url.getDefaultPort());
		System.out.println("File = " + url.getFile());
		System.out.println("Host = " + url.getHost());
		System.out.println("Path = " + url.getPath());
		System.out.println("Port = " + url.getPort());
		System.out.println("Protocol = " + url.getProtocol());
		System.out.println("Query = " + url.getQuery());
		System.out.println("Ref = " + url.getRef());
		System.out.println("User Info = " + url.getUserInfo());

		System.out.print('\n');

		InputStream is = url.openStream();

		int ch;
		while ((ch = is.read()) != -1) {
			System.out.print((char) ch);
		}
		is.close();
	}

	public void readUrl() throws Exception {
		URL url = new URL("http://localhost:8088/gress/data/reportData_201401.xml?a=b");

		System.out.println("Protocol = " + url.getProtocol());
		System.out.println("Host =" + url.getHost());
		System.out.println("Port =" + url.getPort());
		System.out.println("Path =" + url.getPath());
		System.out.println("File =" + url.getFile());
		System.out.println("Ref =" + url.getRef());
		System.out.println("Query =" + url.getQuery());

		URLConnection urlConn = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			System.out.println(str);
		}

		bufferedReader.close();
	}

	public void testHttpUrlConnection() throws Exception{
		URL url=new URL("http://banshee.cs.uow.edu.au:2000/~nabg/echo.cgi");
		URLConnection uc=url.openConnection();

		//验证连接的类型，必须是HttpURLConnection的
		if (!(uc instanceof HttpURLConnection)){
			System.err.println("Wrong connection type");
			return;
		}

		//必须能把名/值对输出到服务器程序资源
		uc.setDoOutput(true);

		//不使用cache
		uc.setUseCaches(false);

		//设置Content-type指示指定MIME类型
		uc.setRequestProperty("Content-type","appliction/x-www-form-urlencoded");

		//建立名/值对内容发送给服务器
		String content=buildContent(new String[]{"content","name3"});

		//连接的适当类型
		HttpURLConnection hc=(HttpURLConnection)uc;

		//把HTTP请求方法设置为POST (默认的是GET)
		hc.setRequestMethod("POST");

		//输出内容
		OutputStream os=uc.getOutputStream();
		DataOutputStream dos=new DataOutputStream(os);
		dos.writeBytes(content);
		dos.flush();
		dos.close();

		//从服务器程序资源输入和显示内容
		InputStream is=uc.getInputStream();

		int ch;
		while ((ch=is.read())!=-1){
			System.out.print((char)ch);
		}

		is.close();
	}

	static String buildContent(String [] args){
		StringBuffer sb=new StringBuffer();

		for (int i=0;i<args.length;i++){
			//对参数编码
			String encodedItem= URLEncoder.encode(args[i]);
			sb.append(encodedItem);

			if (i%2==0){
				sb.append("=");//分离名称和值
			}else {
				sb.append("&");//分离名称/值对
			}
		}

		//删除最后的&间隔符
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
}
