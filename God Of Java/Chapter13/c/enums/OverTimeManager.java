package c.enums;

public class OverTimeManager {
    public static void main(String []args) {
        OverTimeManager manager = new OverTimeManager();
        int myAmount = manager.getOverTimeAmount(OverTimeValues.THREE_HOUR);
        System.out.println(myAmount);
    }
    
    public int getOverTimeAmount(OverTimeValues value) {
        int amount = 0;
        System.out.println(value);
        switch(value) {
            case THREE_HOUR:
                amount = 18000;
                break;
            case FIVE_HOUR:
                amount = 30000;
                break;
            case WEEKEND_FOUR_HOUR:
                amount = 40000;
                break;
            case WEEKEND_EIGHT_HOUR:
                amount = 60000;
                break;
        }
        return amount;
    }
}