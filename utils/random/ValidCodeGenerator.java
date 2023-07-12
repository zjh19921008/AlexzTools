package random;
import java.util.Random;

/*
    要求:
        随机生成一个6位数的验证码,验证码中可以有大写字母,小写字母,至少有一个数字;
    分析:
        随机生成6位数的验证码,必须会使用Random;
        而Random只能生成数字,思考,如何将数字转换成字符呢?
        思路1:
        1: 提前写出来所有的合法的字符,然后,将生成的随机数当成索引使用;
        A-Z  a-z  0-9
        为了保证至少有一个数字,可以利用setChar方法,随机将已经存在的字符串中的某一个字符修改为我们的数字;
        思路2:
        2: 直接将生成的随机数利用强制类型转换,根据ASCII码值,将数字转成字符;
        可以将随机数的范围设定为  65  -  122 然后再利用if语句,去除不合法的数据;


 */
public class ValidCodeGenerator {
    public static void main(String[] args) {
        String s = yzm1();
        System.out.println("使用思路1生成的验证码是:"+s);
        String s2 = yzm2();
        System.out.println("使用思路2生成的验证码是:"+s2);
    }
    //思路1:
    public static String yzm1(){
        //提前写出来所有的合法的字符,然后,将生成的随机数当成索引使用;
        StringBuilder sb = new StringBuilder();
        // 直接利用for循环,可以将所有合法的字符添加到sb对象中
        for (char i = 'A'; i <='Z' ; i++) {
            sb.append(i);
        }
        for (char i = 'a'; i <='z' ; i++) {
            sb.append(i);
        }
        for (char i = '0'; i <='9' ; i++) {
            sb.append(i);
        }
        //System.out.println(sb);
        //到此处,所有的合法字符都已经保存到了sb对象中
        //sb2用于保存最终生成的验证码
        StringBuilder sb2 = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i <6; i++) {
            //每循环一次,从sb中随机获取一个字符,并添加到sb2中
            int index=r.nextInt(sb.length());
            char c = sb.charAt(index);//从sb中随机获取一个字符
            sb2.append(c);//添加到sb2中
        }
        //System.out.println(sb2);
        //为了保证至少有一个数字,可以利用setCharAt方法,随机将已经存在的字符串中的某一个字符修改为我们的数字;
        //随机生成0  9之间的一个字符
        int i=r.nextInt(57-48+1)+48;
        char c =(char)i;
        int index = r.nextInt(sb2.length());
        sb2.setCharAt(index,c);//至少有一个数字;
        return sb2.toString();
    }
    //思路2:
    public static String yzm2(){
        /*
         2: 直接将生成的随机数利用强制类型转换,根据ASCII码值,将数字转成字符;
        可以将随机数的范围设定为  65  -  122 然后再利用if语句,去除不合法的数据;
         */
        //sb2用于保存最终生成的验证码
        StringBuilder sb2 = new StringBuilder();
        Random r = new Random();
        //循环6次,生成6个字符,添加到sb2对象中
        for (int i = 0; i <6 ; i++) {
            //每循环一次,生成一个65--122之间的数字,
            int zhi=r.nextInt(122-65+1)+65;
            //利用if语句,去除不合法的数据;
            if(zhi>=91&&zhi<=96){
                i--;// 将这一次循环的机会补回来
                continue;
            }
            //当代码执行到这里的时候,证明这个  zhi是合法的
            sb2.append((char) zhi);
        }
        //为了保证至少有一个数字,可以利用setCharAt方法,随机将已经存在的字符串中的某一个字符修改为我们的数字;
        //随机生成0  9之间的一个字符
        int i=r.nextInt(57-48+1)+48;
        char c =(char)i;
        int index = r.nextInt(sb2.length());
        sb2.setCharAt(index,c);//至少有一个数字;
        return sb2.toString();
    }
}