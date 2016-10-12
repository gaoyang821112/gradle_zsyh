package com.gaoyang.test.equals_hashcode;

import com.gaoyang.test.equals_hashcode.bean.Person1;
import com.gaoyang.test.equals_hashcode.bean.Person2;

import java.util.HashSet;
import java.util.Iterator;

public class BasicEqual2 {
    public static void main(String argv[]) {
        Person1 p1 = new Person1("lee", "changsha", "123");
        Person1 p2 = new Person1("lee", "changsha", "123");

        HashSet<Person1> set1 = new HashSet<Person1>();
        set1.add(p1);
        set1.add(p2);

        Iterator<Person1> it1 = set1.iterator();
        while (it1.hasNext()) {
            Person1 p = it1.next();
            System.out.println(p + " " + p.getName());
        }


        Person2 p3 = new Person2("lee", "changsha", "123");
        Person2 p4 = new Person2("lee", "changsha", "123");

        HashSet<Person2> set2 = new HashSet<Person2>();
        set2.add(p3);
        set2.add(p4);

        Iterator<Person2> it2 = set2.iterator();
        while (it2.hasNext()) {
            Person2 p = it2.next();
            System.out.println(p + " " + p.getName());
        }
    }
}  