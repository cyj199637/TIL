package d.collection;

import java.util.ArrayList;

public class ListSample {
    public static void main(String []args) {
        ListSample sample = new ListSample();
        // sample.checkArrayList1();
        // sample.checkArrayList2();
        // sample.checkArrayList3();
        // sample.checkArrayList4();
        // sample.checkArrayList5();
        // sample.checkArrayList6();
        // sample.checkArrayList7();
        sample.checkArrayList8();
    }

    public void checkArrayList1() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("ArrayListSample");

        ArrayList<String> list2 = new ArrayList<>(20);
    }

    public void checkArrayList2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add(1, "F");
        System.out.println(list);
    }

    public void checkArrayList3() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.add("E");
        list1.add(1, "F");

        ArrayList<String> list2 = new ArrayList<>(list1);
        list2.add("0 ");
        // list2.addAll(list1);
        System.out.println("List2: "+list2);
    }

    public void checkArrayList4() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");

        ArrayList<String> list2 = list1;
        list1.add("Ooops");
        System.out.println("List2: "+list2);
    }

    public void checkArrayList5() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        int listSize = list1.size();

        for (int loop=0; loop<listSize; loop++) {
            System.out.println("list.get("+loop+") = "+list1.get(loop));
        }
    }

    public void checkArrayList6() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        String[] strList = list1.toArray(new String[0]);
        System.out.println(strList[0]);
    }

    public void checkArrayList7() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        String[] tempArray = new String[0];
        String[] strList = list1.toArray(tempArray);
        for (String temp:strList) {
            System.out.println(temp);
        }
    }

    public void checkArrayList8() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("A");
        // System.out.println("Removed "+list1.remove(0));
        // System.out.println(list1.remove("A"));
        ArrayList<String> temp = new ArrayList<>();
        temp.add("A");
        list1.removeAll(temp);
        for (int loop=0; loop<list1.size(); loop++) {
            System.out.println("list.get("+loop+") = "+list1.get(loop));
        }
    }
}