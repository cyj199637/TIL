package c.enums;

public class OverTimeManager2 {
    public static void main(String []args) {
        OverTimeValues2 value2 = OverTimeValues2.FIVE_HOUR;
        System.out.println(value2);
        System.out.println(value2.getAmount());

        OverTimeValues2 value3 = OverTimeValues2.THREE_HOUR;
        System.out.println(value2.compareTo(value3));

        OverTimeValues2 []valueList = OverTimeValues2.values();
        for (OverTimeValues2 value:valueList) {
            System.out.println(value);
        }
    }
}