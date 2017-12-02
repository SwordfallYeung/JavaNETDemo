package b_abstract_class.ResponseCache;

import b_abstract_class.ResponseCache.ResponseCacheDemo;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author y15079
 * @create 2017-12-02 16:44
 * @desc
 *
 *测试CacheResponse、CacheRequest、ResponseCache三者的关系
 *
 * 执行失败，不知道为什么
 **/
public class ResponseCacheTest {
	public static void main(String[] args) {

		//安装系统级缓存
		ResponseCacheDemo responseCacheDemo=new ResponseCacheDemo();
		ResponseCacheDemo.setDefault(responseCacheDemo);
		while (true){
			try {
				Scanner scanner=new Scanner(System.in);
				String str=scanner.nextLine();
				URL url=new URL(str);
				URLConnection connection=url.openConnection();
				connection.setUseCaches(true);
				System.out.println();
				responseCacheDemo.displayHashMap();
				System.out.println();

				//执行顺序是connection.getInputStream()的时候
				//会去执行ResponseCache的get方法
				//如果mCache中有，那么直接从mCache中取出
				//如果没有，那么就执行put之后再执行get
				//通过ResponseCache的get的CacheResponse来获取InputStreamReader
				BufferedInputStream bis=new BufferedInputStream(connection.getInputStream());
				Reader reader=new InputStreamReader(bis);
				int c;
				//只显示10个字符的缓存内容
				int count=10;
				while (count >= 0 && (c=reader.read())!=-1){
					System.out.println((char)c);
					count--;
				}
				System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
