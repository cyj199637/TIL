package c.inheritance;

public class ManageStudent {
    public static void main(String []args) {
        ManageStudent manager = new ManageStudent();
        manager.checkEquals();
    }

    public void checkEquals() {
        Student a = new Student("Lora", "Suwon", "01012341234", "ask@godofjava.com");
        Student b = new Student("Lora", "Suwon", "01012341234", "ask@godofjava.com");

        if (a.equals(b)) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }
}