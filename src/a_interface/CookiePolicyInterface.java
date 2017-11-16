package a_interface;

/**
 * @author y15079
 * @create 2017-11-15 16:53
 * @desc
 *
 * cookie策略
 *
 * 接口
 *
 * CookiePolicy 实现类把cookies分为接收的和拒绝的。
 * 提供三种预定义的策略实现，分别为ACCEPT_ALL，ACCEPT_NONE和 ACCEPT_ORIGINAL_SERVER
 *
 * 属性：
 *   ACCEPT_ALL               预定义策略接受全部cookies
 *   ACCEPT_NONE              预定义策略不接口cookies
 *   ACCEPT_ORIGINAL_SERVER   预定义策略只接受来自原来服务的cookies
 *
 * 方法：
 *    shouldAccept(URI uri, HttpCookie cookie)   将会被调用查看这个cookies是否应该被接受
 *
 * Since 1.6
 **/
public class CookiePolicyInterface {
}
