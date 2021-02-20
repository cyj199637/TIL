public class ControlLabel {
    public static void main(String arags[]) {
        ControlLabel control = new ControlLabel();
        // control.printTimesTable();
        // control.printTimesTableSkip4Case1();
        // control.printTimesTableSkip4Case2();
        // control.printTimesTableSkipAfter4Case1();
        control.printTimesTableSkipAfter4Case2();
    }

    public void printTimesTable() {
        for (int loop1=2; loop1<10; loop1++) {
            for (int loop2=1; loop2<10; loop2++) {
                System.out.print(loop1 + " * " + loop2 + " = " + (loop1 * loop2));
            }
            System.out.println();
        }
    }

    public void printTimesTableSkip4Case1() {
        for (int loop1=2; loop1<10; loop1++) {
            for (int loop2=1; loop2<10; loop2++) {
                if (loop1 == 4 || loop2 == 4) continue;
                System.out.print(loop1 + " * " + loop2 + " = " + (loop1 * loop2));
            }
            System.out.println();
        }
    }

    public void printTimesTableSkip4Case2() {
        for (int loop1=2; loop1<10; loop1++) {
            if (loop1 == 4) continue;
            for (int loop2=1; loop2<10; loop2++) {
                if (loop2 == 4) continue;
                System.out.print(loop1 + " * " + loop2 + " = " + (loop1 * loop2));
            }
            System.out.println();
        }
    }

    public void printTimesTableSkipAfter4Case1() {
        for (int loop1=2; loop1<10; loop1++) {
            for (int loop2=1; loop2<10; loop2++) {
                if (loop2 == 4) break;
                System.out.print(loop1 + " * " + loop2 + " = " + (loop1 * loop2));
            }
            System.out.println();
        }
    }

    public void printTimesTableSkipAfter4Case2() {
        startLabel:
        for (int loop1=2; loop1<10; loop1++) {
            for (int loop2=1; loop2<10; loop2++) {
                if (loop2 == 4) continue startLabel;
                System.out.print(loop1 + " * " + loop2 + " = " + (loop1 * loop2));
            }
            System.out.println();
        }
    }
}