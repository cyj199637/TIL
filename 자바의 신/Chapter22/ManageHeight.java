import java.util.ArrayList;

public class ManageHeight {
    public static void main(String[] args) {
        ManageHeight manager = new ManageHeight();
        manager.setData();
        for (int loop=1; loop<6; loop++) {
            // manager.printHeight(loop);
            manager.printAverage(loop);
        }
    }
    
    ArrayList<Integer[]> gradeHeights = new ArrayList<Integer[]>();
    
    public void setData() {
        gradeHeights.add(new Integer[]{170, 180, 173, 175, 177});
        gradeHeights.add(new Integer[]{160, 165, 167, 186});
        gradeHeights.add(new Integer[]{158, 177, 187, 176});
        gradeHeights.add(new Integer[]{173, 182, 181});
        gradeHeights.add(new Integer[]{170, 180, 165, 177, 172});
    }

    public void printHeight(int classNo) {
        System.out.println("Class No.: "+classNo);
        Integer[] classList = gradeHeights.get(classNo-1);
        for (int one:classList) {
            System.out.println(one);
        }
    }

    public void printAverage(int classNo) {
        Integer[] classList = gradeHeights.get(classNo-1);
        double sum = 0;
        double heightAvg;

        for (int one:classList) {
            sum += one;
        }
        heightAvg = (sum / classList.length);

        System.out.println("Class No.: "+classNo);
        System.out.println("Height average: "+heightAvg);
    }
}