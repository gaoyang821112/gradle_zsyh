package lesson28;

/**
 * Created by gaoyang on 2016/7/17.
 */
public class TestEquals {

    public static void main(String[] args) {
        TestEquals object = new TestEquals();
        //16进制地址
        System.out.println(object);
        System.out.println(object.toString());

        String s1 = "aaa";
        System.out.println(s1);
        System.out.println(s1.toString());

        String s2 = "aaa";
        //字符串池里面已有aaa，所以没有创建对象（字面值方式赋值）
        System.out.println("s1 == s2 " + (s1 == s2));

        String a1 = new String("aaa");
        String a2 = new String("aaa");

        //由于指向的是堆中地址引用，new就会创建新的对象，存放在堆heap中,所以为false
        System.out.println("a1 == a2 " + (a1 == a2));
        //判断字符串内容是否相等（String是单独判断）
        System.out.println("a1 equals a2 " + (a1.equals(a2)));
        System.out.println("a1 intern s2 " + ((a1.intern() == s2.intern())));

        String str1 = "hello";
        String str2 = "hel";
        String str3 = "lo";
        //字符串池里不存在相等关系
        System.out.println("str1 == (str2 + str3) " + (str1 == (str2 + str3)));
        //字符串池里存在相等关系
        System.out.println("str1 == (hel + lo) " + (str1 == ("hel" + "lo")));

        Object object1 = new Object();
        Object object2 = new Object();

        //如果是Object类，equals 和 == 是相同的，请参见源代码
        System.out.println("object1 equals object2 " + (object1.equals(object2)));

        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        //同理 地址和值得比较
        System.out.println("i1 == i2 " + (i1 == i2));
        System.out.println("i1 equals i2 " + (i1.equals(i2)));

        StringBuffer sb = new StringBuffer();
        sb.append("hello").append(" world");


        //基础类型不会被赋值交换
        Integer a = 1;
        Integer b = 2;
        TestEquals.swap(a, b);
        System.out.println(a);
        System.out.println(b);

    }

    public static void swap(int a, int b){
        int temp = a;
        a = b;
        b = temp;

    }



    @Override
    public String toString() {
        return super.toString();
    }
}
