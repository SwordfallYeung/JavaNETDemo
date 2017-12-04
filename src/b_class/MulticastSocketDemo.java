package b_class;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

/**
 * @author SwordFall
 * @create 2017-12-03 20:36.
 * @desc
 *
 * 应用程序只将数据包发送给组播地址.路由器将确保包被发送到改组播组中的所有主机.
 * 组播地址:称为组播组的一组主机所共享的地址.组播地址的范围在224.0.0.0---239.255.255.255之间(都为D类地址1110开头)
 **/
//让该类实现Runnable接口,该类的实例可作为线程的target
public class MulticastSocketDemo implements Runnable{

    //使用常量作为本程序的多点广播IP地址
    private static final String BROADCAST_IP="230.0.1.1";
    //使用常量作为本程序的多点广播目的的端口
    public static final int BROADCAST_PORT=30000;
    //定义每个数据报的最大大小为4k
    private static final int DATA_LEN=4096;
    //定义本程序的MulticastSocket实例
    private MulticastSocket multicastSocket=null;
    private InetAddress broadcastAddress=null;
    private Scanner scan=null;
    //定义接收网络数据的字节数组
    byte[] inBuff=new byte[DATA_LEN];
    //以指定字节数组创建准备接收数据的DatagramPacket对象
    private DatagramPacket inPacket=new DatagramPacket(inBuff,inBuff.length);
    //定义一个用于发送的DatagramPacket对象
    private DatagramPacket outPacket=null;


    public void init() throws Exception{
        try {
            //创建用于发送,接收数据的MulticastSocket对象
            //因为该MulticastSocket对象需要接收,所以有指定端口
            multicastSocket=new MulticastSocket(BROADCAST_PORT);
            broadcastAddress=InetAddress.getByName(BROADCAST_IP);

            //将该socket加入指定的多点广播地址
            multicastSocket.joinGroup(broadcastAddress);
            //设置本MulticastSocket发送的数据报被回送到自身
            multicastSocket.setLoopbackMode(false);
            //初始化发送用的DatagramSocket,它包含一个长度为0的字节数组
            outPacket=new DatagramPacket(new byte[0],0,broadcastAddress,BROADCAST_PORT);

            //启动以本实例的run()方法作为线程体的线程
            new Thread(this).start();
            //创建键盘输入流
            scan=new Scanner(System.in);
            //不断读取键盘输入
            while (scan.hasNextLine()){
                //将键盘输入的一行字符串转换字节数组
                byte[] buff=scan.nextLine().getBytes();
                //设置发送用的DatagramPacket里的字节数据
                outPacket.setData(buff);
                //发送数据报
                multicastSocket.send(outPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            multicastSocket.close();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                //读取Socket中的数据,读到的数据放在inPacket所封装的字节数组里.
                multicastSocket.receive(inPacket);
                //打印输出从socket中读取的内容
                System.out.println("test聊天信息: "+new String(inBuff,0,inPacket.getLength()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (multicastSocket!=null){
                    //让该Socket离开该多点IP广播地址
                    multicastSocket.leaveGroup(broadcastAddress);
                    //关闭该Socket对象
                    multicastSocket.close();
                }
                System.exit(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MulticastSocketDemo().init();
    }
}
