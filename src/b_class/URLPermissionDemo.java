package b_class;

/**
 * @author SwordFall
 * @create 2017-12-22 00:37.
 * @desc
 *
 * 父类Permission
 * 构造函数
 * URLPermission(String url) 默认为URLPermission(url, "*:*")
 * URLPermission(String url, String actions)
 *
 * actions为:
 * "POST,GET,DELETE"
    "GET:X-Foo-Request,X-Bar-Request"
    "POST,GET:Header1,Header2"
 *
 * 主要方法:
 * getActions()             Returns the normalized method list and request header list, in the form:
 * implies(Permission p)    Checks if this URLPermission implies the given permission.
 *
 **/
public class URLPermissionDemo {
}
