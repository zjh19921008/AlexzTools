package format;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class URIEncodeTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("默认编码："+ Charset.defaultCharset());
        String org = "测试";
        System.out.println(org + "的Unicode 码点为： " + Integer.toHexString(org.codePointAt(0)).toUpperCase());
        String s = URLDecoder.decode(org, "GBK");
        System.out.println(s);
        byte[] gbks = org.getBytes("GBK");
        for (byte gbk : gbks) {
            System.out.print(gbk + " ");
        }
        System.out.println();
        byte[] bytes = org.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        System.out.println();
        char a = 0xb2,b=0xe2,c=0xca,d=0xd4;
        System.out.println((int)a+" "+(int)b+" "+(int)c+" "+(int)d);
        char[] ascii = {a,b,c,d};
        byte[] ascii1 = {(byte) 0xb2, (byte) 0xe2, (byte) 0xca, (byte) 0xd4};
        String s1 = new String(ascii);
        System.out.println(s1);
        String s2 = new String(ascii1,"GBK");
        System.out.println("原文："+s2);
        String s2u = URLEncoder.encode(s2, "UTF-8");
        System.out.println("前端encodeURI："+s2u);
        String s2ud = URLDecoder.decode(s2u, "GBK");
        System.out.println("后台默认解码："+s2ud);
        String s2udu = URLEncoder.encode(s2ud, "GBK");
        System.out.println("后台手动处理GBK编码："+s2udu);
        String s2dudug = URLDecoder.decode(s2udu, "UTF-8");
        System.out.println("后台手动处理UTF-8编码："+s2dudug);
    }
}
