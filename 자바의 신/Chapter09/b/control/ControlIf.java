package b.control;

public class ControlIf {
    public static void main(String args[]) {
        ControlIf control = new ControlIf();
        // control.ifStatement();
        // control.ifElseStatement();
        // control.ifAndOr();
        // control.tripleAndOr();
        control.elseIf(85);
    }

    public void ifStatement() {
        if(true);
        if(true) System.out.println("It's true");
        if(true)
            System.out.println("It's also true");
        if(false) System.out.println("It's false");
    }

    public void ifElseStatement() {
        int intValue = 5;

        if(intValue > 5) System.out.println("It's bigger than 5.");
        else System.out.println("It's smaller or equal than 5.");

        if(intValue <= 5)
            System.out.println("It's smaller or equal than 5.");
        else
            System.out.println("It's bigger than 5.");

    }

    public void ifAndOr() {
        int age = 25;
        boolean isMarried = true;

        if (age > 25 && isMarried) {
            System.out.println("Age is over 25 and married");
        }
        if (age > 25 || isMarried) {
            System.out.println("Age is over or married");
        }
    }

    public void tripleAndOr() {
        int age = 25;
        boolean isMarried = true;
        double height = 180;

        if ((age > 25 || isMarried) && height >= 180) {
            System.out.println("Age is over 25 or married and height is over 180");
        }
    }

    public void elseIf(int point) {
        if (point > 90) {
            System.out.println("A");
        } else if (point > 80) {
            System.out.println("B");
        } else if (point > 70) {
            System.out.println("C");
        } else if (point > 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}