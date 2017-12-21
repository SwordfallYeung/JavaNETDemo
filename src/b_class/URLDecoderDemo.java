package b_class;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author SwordFall
 * @create 2017-12-22 00:29.
 * @desc
 * URLDecoder 和 URLEncoder 用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换。
 *
 * URLDecoder类包含一个decode(String s,String enc)静态方法，它可以将application/x-www-form-urlencoded MIME字符串转成普通字符串；
 * URLEncoder类包含一个encode(String s,String enc)静态方法，它可以将普通字符串转换成application/x-www-form-urlencoded MIME字符串。
 *
 * URLDecoder的一个应用场景就是解决GET请求的中文乱码问题
 *
 * http://blog.csdn.net/justloveyou_/article/details/57156039
 **/
public class URLDecoderDemo {
    public static void main(String[] args) throws Exception{
        // 将application/x-www-form-urlencoded字符串转换成普通字符串
        // 其中的字符串直接从上图所示窗口复制过来,chrome 默认用 UTF-8 字符集进行编码，所以也应该用对应的字符集解码
        System.out.println("采用UTF-8字符集进行解码:");
        String keyWord = URLDecoder.decode("%E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6+Rico", "UTF-8");
        System.out.println(keyWord);
        System.out.println("\n 采用GBK字符集进行解码:");
        System.out.println(URLDecoder.decode("%E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6+Rico", "GBK"));

        // 将普通字符串转换成application/x-www-form-urlencoded字符串
        System.out.println("\n 采用utf-8字符集:");
        String urlStr = URLEncoder.encode("天津大学", "utf-8");
        System.out.println(urlStr);
        System.out.println("\n 采用GBK字符集:");
        String urlStr2 = URLEncoder.encode("天津大学", "GBK");
        System.out.println(urlStr2);

    }
}
/* Output:
        采用UTF-8字符集进行解码:
        天津大学 Rico

        采用GBK字符集进行解码:
        澶╂触澶у Rico

        采用utf-8字符集:
        %E5%A4%A9%E6%B4%A5%E5%A4%A7%E5%AD%A6

        采用GBK字符集:
        %CC%EC%BD%F2%B4%F3%D1%A7
 *///:~