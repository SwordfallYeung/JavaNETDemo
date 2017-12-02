package b_abstract_class;

import java.io.*;
import java.net.CacheResponse;
import java.util.List;
import java.util.Map;

/**
 * @author y15079
 * @create 2017-12-02 15:39
 * @desc
 *
 * 抽象类
 *
 * 从ResponseCache检索资源的代表通道。这样一个类实例提供一个能返回整个body的InputStream
 * 和能返回有关联的response headers的getHeaders()方法
 *
 * 表示从 ResponseCache 获取资源的通道。这种类的实例提供返回实体正文的 InputStream，
 * 同时提供一个返回关联响应头的getHeaders() 方法。
 **/
public class CacheResponseDemo extends CacheResponse{

	Map<String,List<String>> headers;
	ByteArrayOutputStream body;

	public CacheResponseDemo(Map<String, List<String>> headers, OutputStream body) {
		this.headers = headers;
		this.body = (ByteArrayOutputStream) body;
	}

	@Override
	public Map<String, List<String>> getHeaders() throws IOException {
		return headers;
	}

	@Override
	public InputStream getBody() throws IOException {
		return new ByteArrayInputStream(body.toByteArray());
	}
}
