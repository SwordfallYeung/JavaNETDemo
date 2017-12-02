package b_abstract_class;

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
 * 这个类Authenticator代表一个对象，通过该对象了解如何验证一个network连接。
 * 通常，它会在用户信息提示下做这个。
 *
 * 应用程序通过在子类覆盖getPasswordAuthentication()方法来使用该类。
 * 这个方法典型地使用getXXX()方法来获取整个请求权限信息。它必须通过与用户交互或一些其他非交互手段来
 * 获取用户名和密码。凭证作为PasswordAuthentication返回值返回。
 *
 * 产生的子类实例通过调用 setDefault(Authenticator)注册到系统中。当要求身份认证时，系统将调用其中一个requestPasswordAuthentication()方法，
 * 该方法又会调用注册对象的getPasswordAuthentication()方法。
 *
 * 所有请求身份验证的方法都有一个失败的默认实现。
 *
 *  JAVA提供一个用于启用身份认证的类,可以支持HTTP协议中的多个认证方式,这个类是java.net.Authenticator,使用方法如下:
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
