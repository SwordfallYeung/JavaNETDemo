package b_class;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author SwordFall
 * @create 2017-12-12 23:42.
 * @desc
 *
 * final类
 * http://blog.csdn.net/z69183787/article/details/52947683
 *
 * AIO
 *
 * 同步阻塞
 * 同步非阻塞
 * 异步非阻塞
 *
 * 理解上述的同步异步, 阻塞与非阻塞
 *
 * http://blog.csdn.net/z69183787/article/details/52947683
 **/
public class StandardSocketOptionsDemo {

    public static void main(String[] args) {

    }


}

 class Server{
    static int PORT=8080;
    static int BUFFER_SIZE=1024;
    static String CHARSET="utf-8";//默认编码
    static CharsetDecoder decoder= Charset.forName(CHARSET).newDecoder();//解码

    int port;
    AsynchronousServerSocketChannel serverSocketChannel;

     public Server(int port) {
         this.port = port;
     }

     private void listen() throws Exception{

         //打开一个服务通道
         //绑定服务端口
         this.serverSocketChannel=AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port), 100);
         this.serverSocketChannel.accept(this, new AcceptHandler());
     }
 }

/**
 * accept到一个请求时的回调
 */
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Server>{
    static int PORT=8080;
    static int BUFFER_SIZE=1024;
    static String CHARSET="utf-8";//默认编码
    static CharsetDecoder decoder= Charset.forName(CHARSET).newDecoder();//解码

     @Override
     public void completed(AsynchronousSocketChannel client, Server attachment) {
         try {
             System.out.println("远程地址:"+client.getRemoteAddress());
             //tcp各项参数
             client.setOption(StandardSocketOptions.TCP_NODELAY,true);
             client.setOption(StandardSocketOptions.SO_SNDBUF, 1024);
             client.setOption(StandardSocketOptions.SO_RCVBUF, 1024);

             if (client.isOpen()){
                 System.out.println("client.isOpen:"+client.getRemoteAddress());
                 final ByteBuffer byteBuffer=ByteBuffer.allocate(BUFFER_SIZE);
                 byteBuffer.clear();
                 client.read(byteBuffer,client,new Readd);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

     @Override
     public void failed(Throwable exc, Server attachment) {

     }
 }

/**
 * Read到请求数据的回调
 */
class ReadHandler implements CompletionHandler<Integer,AsynchronousSocketChannel>{
    private ByteBuffer byteBuffer;

    public ReadHandler(ByteBuffer byteBuffer) {
        this.byteBuffer = byteBuffer;
    }

    @Override
    public void completed(Integer result, AsynchronousSocketChannel attachment) {
        if (result<0){//客户端关闭了连接
            Server.clo
        }
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

    }
}
