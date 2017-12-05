package b_class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author SwordFall
 * @create 2017-12-05 21:58.
 * @desc
 *
 * 父类为Object
 * 子类为SSLServerSocket
 *
 * ServerSocket与Socket的关系
 **/
public class ServerSocketDemo {

    public static void main(String[] args) {
        System.out.println("Server...\n");
    }

    public class client{
        public static final int port=8080;
        public static final String host="localhost";

        public void init(){
            System.out.println("Client Start...");
            while (true){
                Socket socket=null;

                try {
                    //创建一个流套接字并将其连接到指定主机上的指定端口号
                    socket=new Socket(host,port);

                    //读取服务器端数据
                    BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    //向服务器端发送数据
                    PrintStream out = new PrintStream(socket.getOutputStream());
                    System.out.print("请输入: \t");
                    String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
                    out.println(str);

                    String ret = input.readLine();
                    System.out.println("服务器端返回过来的是: " + ret);
                    // 如接收到 "OK" 则断开连接
                    if ("OK".equals(ret)) {
                        System.out.println("客户端将关闭连接");
                        Thread.sleep(500);
                        break;
                    }

                    out.close();
                    input.close();

                } catch (Exception e) {
                    System.out.println("客户端异常:" + e.getMessage());
                }finally {
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            socket = null;
                            System.out.println("客户端 finally 异常:" + e.getMessage());
                        }
                    }
                }

            }
        }
    }

    public class Server{
        public static final int port=8080;//监听的端口号

        public void init() {

            try {
                //创建一个ServerSocket,这里可以指定连接请求的队列长度
                //new ServerSocket(port,3);意味着当队列有3个连接请求时,如果Client再请求连接,就会被server拒绝
                ServerSocket serverSocket=new ServerSocket(port);
                while (true){
                    //从请求队列在取出一个连接
                    Socket client=serverSocket.accept();
                    //处理这次连接
                    new HandlerThread(client);
                }
            } catch (IOException e) {
                System.out.println("服务器异常: "+e.getMessage());
            }
        }
    }

    private class HandlerThread implements Runnable{
        private Socket socket;

        public HandlerThread(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {

            try {
                //读取客户端数据
                BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInputStr=input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛出异常
                //处理客户端数据
                System.out.println("客户端发过来的内容:"+clientInputStr);

                //向客户端回复信息
                PrintStream out=new PrintStream(socket.getOutputStream());
                System.out.println("请输入:\t");
                //发送键盘输入的一行
                String s=new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.println(s);

                out.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        socket=null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }


}
