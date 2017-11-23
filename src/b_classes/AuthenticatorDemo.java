package b_classes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * @author SwordFall
 * @create 2017-11-21 21:18.
 * @desc
 *
 * 抽象类
 *
 *  JAVA提供一个用于启用身份认证的类,可以支持HTTP协议中的多个认证方式,这个类是java.net.Authenticator,使用方法如下:
 *
 *
 *
 **/
public class AuthenticatorDemo {

    static final String user="username"; //用户名
    static final String pass="password";//密码

    static class MyAuthenticator extends Authenticator{

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return (new PasswordAuthentication(user,pass.toCharArray()));
        }
    }

    public static void main(String[] args) throws Exception{
        Authenticator.setDefault(new MyAuthenticator());
        URL url=new URL(args[0]);
        InputStream ins=url.openConnection().getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(ins));
        String str;
        while ((str=reader.readLine())!=null)
            System.out.println(str);
    }
}
