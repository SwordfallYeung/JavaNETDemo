package b_class;

/**
 * @author y15079
 * @create 2017-12-12 20:05
 * @desc
 *
 * 抽象类
 * 接口为SocketOptions
 *
 * 抽象类SocketImpl是所有实际实现套接字类的一般超类。它是用于创建客户端和服务器端套接字的。
 * 变量：
 * InetAddress address 远程终端套接字的IP地址
 * int port 远程主机中和这个套接字连接的端口号码
 * int localport 和这个套接字连接的本地端口号
 * int port 套接字绑定的远程主机的端口号码
 *
 * 方法：
 * create(boolean stream) 创建一个流或者数据包套接字
 * connect(String host, int port) 将套接字连接到主机的指定端口上
 * connect(InetAddress address, int port) 将套接字连接到指定主机的指定端口上
 * connect(SocketAddress address, int timeout)
 * bind(InetAddress host, int port)
 * listen(int backlog)
 **/
public class SocketImplDemo {
}
