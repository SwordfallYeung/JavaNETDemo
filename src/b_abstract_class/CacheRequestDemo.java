package b_abstract_class;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

/**
 * @author y15079
 * @create 2017-11-24 19:48
 * @desc
 *
 * 抽象类
 *
 * 在ResponseCache中存储资源的代表通道。这个类的实例提供了一个能被protocol handlers调用去在cache存储数据OutputStream对象，和
 * 一个abort()方法，该方法允许cache存储操作被中断和放弃。
 *
 *表示在 ResponseCache 中存储资源的通道。这种类的实例提供一个 OutputStream 对象，协议处理程序可以调用该对象来将资源数据存储到缓存中
 **/
public class CacheRequestDemo  extends CacheRequest{

	final ByteArrayOutputStream body=new ByteArrayOutputStream();

	@Override
	public OutputStream getBody() throws IOException {
		return body;
	}

	@Override
	public void abort() {

	}
}
