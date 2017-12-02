package b_abstract_class;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

/**
 * @author SwordFall
 * @create 2017-12-03 01:34.
 * @desc
 *
 * 参考DatagramPacketDemo的解释
 **/
public class DatagramSocketDemo {

    public static void main(String[] args) throws Exception{
            new Receive().receive();
    }

    public static class Receive{
        public void receive() throws Exception{

            //接收消息

            //确定接收方的IP和端口号,IP地址为本地机器地址
            InetAddress ip=InetAddress.getLocalHost();
            int port=8888;

            //创建接收方的套接字,并制定端口号和IP地址
            DatagramSocket getSocket=new DatagramSocket(port,ip);

            //确定数据报接收的数据的数组大小
            byte[] buf=new byte[1024];

            //创建接收类型的数据报,数据将存储在buf中
            DatagramPacket getPacket=new DatagramPacket(buf,buf.length);

            //通过套接字接收数据
            getSocket.receive(getPacket);

            //解析发送方传递的消息,并打印
            String getMes=new String(buf,0,getPacket.getLength());
            System.out.println("对方发送的消息:"+getMes);

            // 通过数据报得到发送方的IP和端口号，并打印
            InetAddress sendIP = getPacket.getAddress();
            int sendPort = getPacket.getPort();
            System.out.println("对方的IP地址是：" + sendIP.getHostAddress());
            System.out.println("对方的端口号是：" + sendPort);

            //////////////////////////////////////////

            //发送反馈消息

            // 通过数据报得到发送方的套接字地址
            SocketAddress sendAddress = getPacket.getSocketAddress();

            // 确定要反馈发送方的消息内容，并转换为字节数组
            String feedback = "接收方说：我收到了！";
            byte[] backBuf = feedback.getBytes();

            // 创建发送类型的数据报
            DatagramPacket sendPacket = new DatagramPacket(backBuf,
                    backBuf.length, sendAddress);

            // 通过套接字发送数据
            getSocket.send(sendPacket);

            // 关闭套接字
            getSocket.close();
        }
    }
}
