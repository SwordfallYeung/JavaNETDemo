package b_class;

import java.net.IDN;

/**
 * @author SwordFall
 * @create 2017-12-03 15:50.
 * @desc
 *
 * final 类
 *
 * Translates a string from Unicode to ASCII Compatible Encoding (ACE),
 * as defined by the ToASCII operation of RFC 3490.
 **/
public class IDNDemo {
    public static void main(String[] args) {
        String input="www.baidu.com";
        String ascii= IDN.toASCII(input);
        String unicode=IDN.toUnicode(input);

        System.out.println("Input:"+input);
        System.out.println("toAscii (input): "+ascii);
        System.out.println("toUnicode (input): "+unicode);
    }
}
