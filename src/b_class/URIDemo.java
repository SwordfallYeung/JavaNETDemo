package b_class;

import java.net.URI;
import java.net.URL;

/**
 * @author SwordFall
 * @create 2017-12-20 21:49.
 * @desc
 *
 * URI可以解码:URI 类在某些特定情况下对其组成字段执行转义。建议使用 URI 管理 URL 的编码和解码，并使用 toURI() 和 URI.toURL() 实现这两个类之间的转换。
   也可以使用 URLEncoder 和 URLDecoder 类，但是只适用于 HTML 形式的编码，它与 RFC2396 中定义的编码机制不同。
 *
 * URI是个纯粹的句法结构，用于指定标识Web资源的字符串的各个不同部分。URL是URI的一个特例，它包含了定位Web资源的足够信息。
 *
 * 在Java类库中，URI类不包含任何访问资源的方法，它唯一的作用就是解析。相反的是，URL类可以打开一个到达资源的流。因此URL类只能作用于那些Java类库知道该如何处理的模式，例如：http：，https：，ftp:，本地文件系统(file:)，和Jar文件(jar:)
 *
 * URI作用:
 * ①URI类的作用之一是解析标识符并将它们分解成各个不同的组成部分。你可以用以下方法读取它们：
    getSchema
    getHost
    getPort
    getPath
    getQuery
 *
 * ② URI类的另一个作用是处理绝对标识符和相对标识符
 * 例如：假设你有一个基本URI：
        http://docs.mycompany/api
        和另一个URI：
        http://docs.mycompany/api/java/lang/String.html
        那么相对化后的URI就是：
        java/lang/String.html
 *
 * -----------------------------------------------------
 * URI、URL和URN是识别、定位和命名网上资源的标准途径。
 *
 * Internet被认为是全球的实际和抽象的资源的集合。实际的资源包括从文件（file）到人(person)，抽象的资源包括数据库查询等。
 * 因为要通过多样的方式识别资源，所以需要标准的识别Internet资源的途径。为了满足这种需要，引入了URI、URL和URN。
 *
 * URI、URL和URN是彼此关联的。URI位于顶层，URL和URN的范畴位于底层。URL和URN都是URI的子范畴。
 *
 * URI翻译为统一资源标识，它是以某种标准化的方式标识资源的字符串。这种字符串以scheme开头，语法如下：
 * [scheme:] scheme-specific-part
 *URI以scheme和冒号开头。冒号把scheme与scheme-specific-part分开，并且scheme-specific-part的语法由URI的scheme决定。
 *例如http://www.cnn.com，其中http是scheme，//www.cnn.com是 scheme-specific-part。
 *URI分为绝对（absolute）或相对（relative）两类。
 *
 * 使用①:
 * Java API通过提供URI类（位于java.net包中），使我们在代码中使用URI成为可能。URI的构造函数建立URI对象，并且分析URI字符串，提取URI组件。URI的方法提供了如下功能：
 * 1）决定URI对象的URI是绝对的还是相对的；2）决定URI对象是opaque还是hierarchical；3）比较两个URI对象；4）标准化（normalize）URI对象；5）根据Base URI解析某个Relative URI；6）根据Base URI计算某个URI的相对URI；7）把URI对象转换为URL对象。
 *在URI里面有多个构造函数，最简单的是URI(String uri)。这个构造函数把String类型的参数URI分解为组件，并把这些组件存储在新的URI对象中。
 *如果String对象的URI违反了RFC 2396的语法规则，将会产生一个java.net.URISyntaxException。
 *
 *下面的代码演示了使用URI(String uri)建立URI对象：
 *URI uri = new URI ("http://www.cnn.com");
 *
 * 使用②:
 *如果知道URI是有效的，不会产生URISyntaxException，可以使用静态的create(String uri)方法。这个方法分解uri，如果没有违反语法规则就建立URI对象，否则将捕捉到一个内部URISyntaxException，并把该对象包装在一个IllegalArgumentException中抛出。
 *下面的代码片断演示了create(String uri)：
 *URI uri = URI.create ("http://www.cnn.com");
 *
 * URI构造函数和create(String uri)方法试图分解出URI的authority的用户信息、主机和端口部分。对于正确形式的字符串会成功，对于错误形式的字符串，他们将会失败。
 *一旦拥有了URI对象，你就可以通过调用getAuthority()、getFragment()、getHost()、getPath()、getPort()、getQuery()、getScheme()、getSchemeSpecificPart()和 getUserInfo()方法提取信息。
 * 以及isAbsolute()、isOpaque()等方法。
 *
 *
 **/
public class URIDemo {
    public static void main(String[] args) throws Exception{

    }

    public void createUri() throws Exception{
        String urlString = "http://192.168.21.77:8080/swp/mainPage?aa=11&bb%3D22";

        URI uri = URI.create(urlString);
        System.out.println(uri.getPath());
        System.out.println(uri.getQuery());//解码

        URL url2 = new URL(urlString);
        System.out.println(url2.getQuery());//不解码

        System.out.println ("Authority = " +uri.getAuthority ());
        System.out.println ("Fragment = " +uri.getFragment ());
        System.out.println ("Host = " +uri.getHost ());
        System.out.println ("Path = " +uri.getPath ());
        System.out.println ("Port = " +uri.getPort ());
        System.out.println ("Query = " +uri.getQuery ());
        System.out.println ("Scheme = " +uri.getScheme ());
        System.out.println ("Scheme-specific part = " + uri.getSchemeSpecificPart ());
        System.out.println ("User Info = " +uri.getUserInfo ());
        System.out.println ("URI is absolute: " +uri.isAbsolute ());
        System.out.println ("URI is opaque: " +uri.isOpaque ());
    }

    //URI类支持基本的操作，包括标准化（normalize）、分解（resolution）和相对化（relativize）。下例演示了normalize()方法。
    public void basicOper() throws Exception{
        URI uri = new URI ("x/y/../z/./q");
        System.out.println ("Normalized URI = " + uri.normalize());
    }

    //URI通过提供resolve(String uri)、resolve(URI uri)和relativize(URI uri)方法支持反向解析和相对化操作。
    public void analayOper() throws Exception{
        URI uriBase = new URI ("http://www.somedomain.com/");
        System.out.println ("Base URI = " +uriBase);

        URI uriRelative = new URI ("x/../y");
        System.out.println ("Relative URI = " +uriRelative);

        URI uriResolved = uriBase.resolve (uriRelative);
        System.out.println ("Resolved URI = " +uriResolved);

        URI uriRelativized = uriBase.relativize (uriResolved);
        System.out.println ("Relativized URI = " +uriRelativized);
    }
}
