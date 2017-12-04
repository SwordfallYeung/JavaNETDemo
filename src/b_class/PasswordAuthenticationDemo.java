package b_class;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * @author y15079
 * @create 2017-12-04 15:58
 * @desc
 *
 * final 类
 *
 * 构造函数：
 * PasswordAuthentication（String userName, char[] password）
 *
 * 方法：
 * getPassword()    返回用户密码
 * getUserName()     返回用户名
 **/
public class PasswordAuthenticationDemo {

	static final String kuser="username";//你的帐号
	static final String kpass="password";//帐号密码

	static class MyAuthenticator extends Authenticator{
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			System.err.println("Feeding username and password for "+getRequestingScheme());
			return (new PasswordAuthentication(kuser,kpass.toCharArray()));
		}
	}

	public static void main(String[] args) throws Exception{
		Authenticator.setDefault(new MyAuthenticator());
		URL url=new URL(args[0]);
		InputStream ins=url.openConnection().getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(ins));
		String str;
		while ((str=reader.readLine())!=null){
			System.out.println(str);
		}
	}

}
