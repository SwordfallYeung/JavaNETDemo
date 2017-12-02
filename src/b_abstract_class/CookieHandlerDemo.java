package b_abstract_class;

import java.net.*;
import java.util.List;

/**
 * @author y15079
 * @create 2017-12-02 18:21
 * @desc
 *
 * 抽象类
 * 直接子类为 CookieManager
 *
 **/
public class CookieHandlerDemo {
	public static void main(String[] args) throws Exception{
		getCookies();
	}

	//从CookieManger获取cookieStore
	//再从cookieStore获取所有的cookie
	public static void getCookies() throws Exception{
		String urlString="http://www.baidu.com";
		CookieManager manager=new CookieManager();
		CookieHandler.setDefault(manager);
		//第一次连接百度
		URL url=new URL(urlString);
		URLConnection connection=url.openConnection();
		Object obj=connection.getContent();

		//第二次连接百度
		url=new URL(urlString);
		connection=url.openConnection();
		obj=connection.getContent();

		//获取cookiesStore
		CookieStore cookieStore=manager.getCookieStore();
		//从cookiesStore获取所有的cookies
		List<HttpCookie> cookies=cookieStore.getCookies();
		for (HttpCookie cookie:cookies){
			System.out.println(cookie);
		}
	}


}
