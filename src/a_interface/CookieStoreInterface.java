package a_interface;

/**
 * @author y15079
 * @create 2017-11-15 17:06
 * @desc
 *
 * CookieStore cookie存储
 *
 * 接口
 *
 * 一个CookieStore对象代表一个cookie的存储。可以存储和获取cookies
 *
 * Cookie管理器将会调用CookieStore的add方法为每个进来的HTTP响应保存cookies，
 * 而且还会调用CookieStore的get方法为每个出去的HTTP请求获取cookies。
 * A CookieStore is responsible for removing HttpCookie instances which have expired.
 *
 * Since 1.6
 *
 * 方法：
 *     add（URI uri ,HttpCookie cookie）  存储Http cookie
 *     get（URI uri）                     根据给的URI获取cookie
 *     getCookies（）                     获取所有的cookie
 *     getURIs()                           获取所有的URIs
 *     remove(URI uri ,HttpCookie cookie)  从store删除一个cookie
 *     removeAll()						   删除cookie store里面所有的cookies
 *
 **/
public class CookieStoreInterface {
}
