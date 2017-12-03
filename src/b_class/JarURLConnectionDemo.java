package b_class;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author SwordFall
 * @create 2017-12-03 18:42.
 * @desc
 *
 * 抽象类
 * 父类为URLConnection
 * 比较JarURLConnection和HttpURLConnection
 *
 * 抽象类
 *
 * 这个类通过JAR协议建立出了一个jar URL的连接
 * JarURLConnection的实例可以引用一个JAR的压缩包或者这种包里的某个文件.
 *
 * 感觉不怎么会用到
 *
 **/
public class JarURLConnectionDemo {

    private final static String JAR_URL = "jar:file:/C:/Users/ashraf_sarhan/simple-bean-1.0.jar!/";
    private final static String JAR_FILE_PATH = "file:/C:/Users/ashraf_sarhan/simple-bean-1.0.jar";
    private static URLClassLoader urlClassLoader;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        try {

            // Create a URL that refers to a jar file in the file system
            URL FileSysUrl = new URL(JAR_URL);

            // Create a jar URL connection object
            JarURLConnection jarURLConnection = (JarURLConnection)FileSysUrl.openConnection();

            // Get the jar file
            JarFile jarFile = jarURLConnection.getJarFile();

            // Get jar file name
            System.out.println("Jar Name: " + jarFile.getName());

            // When no entry is specified on the URL, the entry name is null
            System.out.println("\nJar Entry: " + jarURLConnection.getJarEntry());

            // Get the manifest of the jar
            Manifest manifest = jarFile.getManifest();

            // Print the manifest attributes
            System.out.println("\nManifest file attributes: ");
            for (Map.Entry entry : manifest.getMainAttributes().entrySet()) {
                System.out.println(entry.getKey() +": "+ entry.getValue());
            }
            System.out.println("\nExternal JAR Execution output: ");

            // Get the jar URL which contains target class
            URL[] classLoaderUrls = new URL[]{new URL(JAR_FILE_PATH)};

            // Create a classloader and load the entry point class
            urlClassLoader = new URLClassLoader(classLoaderUrls);

            // Get the main class name (the entry point class)
            String mainClassName = manifest.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);

            // Load the target class
            Class beanClass = urlClassLoader.loadClass(mainClassName);

            // Get the main method from the loaded class and invoke it
            Method method = beanClass.getMethod("main", String[].class);

            // init params accordingly
            String[] params = null;

            // static method doesn't have an instance
            method.invoke(null, (Object) params);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
