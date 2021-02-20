public class ManageHeight {
    int [][] gradeHeights;

    public static void main(String [] args) {
        ManageHeight manager = new ManageHeight();
        manager.setData();
        for(int classNo=1; classNo<=5; classNo++){
            // manager.printHeight(classNo);
            manager.printAverage(classNo);
        }
    }

    public void setData() {
        gradeHeights = new int[5][];
        gradeHeights[0] = new int[]{170, 180, 173, 175, 177};
        gradeHeights[1] = new int[]{160, 165, 167, 186};
        gradeHeights[2] = new int[]{158, 177, 187, 176};
        gradeHeights[3] = new int[]{173, 182, 181};
        gradeHeights[4] = new int[]{170, 180, 165, 177, 172};
    }
    
    public void printHeight(int classNo) {
        System.out.println("Class No.:"+classNo);
        int [] array = gradeHeights[classNo-1];
        for(int one:array) {
            System.out.println(one);
        }
    }

    public void printAverage(int classNo) {
        double sum = 0;
        int [] array = gradeHeights[classNo-1];
        for(int one:array) {
            sum += one;
        }
        double avg = sum / array.length;
        System.out.println("Class No.:"+classNo);
        System.out.println("Height average:"+avg);
    }
}