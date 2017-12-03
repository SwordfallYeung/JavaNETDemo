package b_class.ResponseCache;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author y15079
 * @create 2017-12-02 16:12
 * @desc
 *
 * 抽象类
 *
 * ResponseCache表示URLConnection 缓存的实现。
 * 这种类的实例可以通过执行ResponseCache.setDefault(ResponseCache) 向系统注册，系统将调用此对象以便：
 * 1. 将从外部源获得的资源数据存储到缓存中
 * 2. 试图获取可能存储在缓存中的请求资源
 *
 * CacheRequest、CacheResponse、ResponseCache这个三者关联
 * 通过ResponseCache可以获取CacheRequest和CacheResponse
 **/
public class ResponseCacheDemo extends ResponseCache {

	private final int maxEntries=2;

	Map<URI, CacheResponse> mCache=new ConcurrentHashMap<>();

	@Override
	public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {

		//只缓存get请求
		if ("GET".equals(rqstMethod)){
			CacheResponse cacheResponse=mCache.get(uri);
			return cacheResponse;
		}else {
			return null;
		}
	}

	@Override
	public CacheRequest put(URI uri, URLConnection conn) throws IOException {

		CacheRequest cacheRequest=(CacheRequest)new CacheRequestDemo();

		//缓存失效机制  由于使用了ConcurrentHashMap 所以这里只是随便选择了一项来删除
		//java中并没有ConcurrentLinkedHashMap so... fifo缓存失效机制没法实现
		if (mCache.size()>=maxEntries){
			Set<Map.Entry<URI,CacheResponse>> entries=mCache.entrySet();
			Map.Entry<URI,CacheResponse> next=entries.iterator().next();
			mCache.remove(next.getKey());
		}

		Map<String,List<String>> headers=conn.getHeaderFields();
		//将CacheRequest的输出流传递给CacheResponse
		CacheResponse cacheResponse=(CacheResponse) new CacheResponseDemo(headers,cacheRequest.getBody());
		mCache.put(uri,cacheResponse);
		return cacheRequest;
	}

	public void displayHashMap(){
		Set<Map.Entry<URI,CacheResponse>> entrySet=mCache.entrySet();
		for (Map.Entry<URI,CacheResponse> entry: entrySet){
			System.out.println("in cache "+entry.getKey()+"--"+entry.getValue());
		}
	}
}
