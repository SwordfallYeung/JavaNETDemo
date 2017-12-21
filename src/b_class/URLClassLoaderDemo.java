package b_class;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author SwordFall
 * @create 2017-12-21 23:07.
 * @desc
 *
 * 父类为 secureClassLoader
 *
 * Java中的类加载器大致可以分成两类,一类是系统提供的,另外一类则是由Java应用开发人员编写的.
 *
 * 系统提供的类加载器主要有下面三个:
 *  ①引导类加载器(bootstrap class loader): 用来加载Java的核心库,是用原生代码来实现的,并不继承自java.lang.ClassLoader.
 *  ②扩展类加载器(extensions class loader): 它用来加载Java的扩展库.java虚拟机的实现会提供一个扩展库目录.该类加载器在此目录里面查找并加载Java类.
 *  ③系统类加载其(system class loader): 它根据Java应用的类路径(CLASSPATH)来加载Java类.一般来说,Java应用的类都是由它来完成加载的.可以通过ClassLoader.getSystemClassLoader()来获取它.
 *
 * 开发人员可以通过继承java.lang.ClassLoader类的方式实现自己的类加载器,以满足一些特殊的需求.
 *
 * 我们平时程序执行的时候在类加载器中寻找类的结构的顺序是：引导类加载器-》 扩展类加载器-》系统类加载器-》我们自定义的一些类加载器，每个类加载器都有自己的空间，同一个加载器里面的类的二进制名字必须是唯一的，
 * 当然同一个类也可以存在不同的加载器内存区域里面，不过我们寻找类的时候是按顺序找的，一但找的也就不会继续往下找了，最终也没找到就会报类不存在异常。
 * 我们如果想动态加载类的话就要仿照我们用的服务器如tomcat和weblogic之类的，他们的开发模式也就是把所有的类都加载到自身的类加载器中，当文件被替换的时候他们就重新加载新的class到内存里面去，从而实现了类的动态加载
 *
 * 使用例子:
 *  File   file   =   new   File(jar文件全路径);
    URL   url   =   file.toURI().toURL();
    URLClassLoader   loader   =   new   URLClassLoader(new   URL[]   {   url   });
    Class   tidyClazz   =   loader.loadClass(所需class的含包名的全名);
 **/
public class URLClassLoaderDemo {
    public static void main(String[] args) throws Exception{
        URL url=new URL("file:/test.jar");
        URLClassLoader myClassLoader=new URLClassLoader(new URL[]{url},Thread.currentThread().getContextClassLoader());
        Class myClass=myClassLoader.loadClass("URLClassLoaderDemo.TestAction");
        ActionInterface action=(ActionInterface)myClass.newInstance();
        String str=action.action();
        System.out.println(str);
    }
}

interface ActionInterface{
    public String action();
}

class TestAction implements ActionInterface{
    @Override
    public String action() {
        return "this ActionTest class";
    }
}