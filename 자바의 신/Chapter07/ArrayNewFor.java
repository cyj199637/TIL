public class ArrayNewFor {
    public static void main(String [] args) {
        ArrayNewFor array = new ArrayNewFor();
        array.newFor();
        array.twoDimFor();
    }

    public void newFor() {
        int [] oneDim = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int data:oneDim) {
            System.out.print(data);
        }
        System.out.println();
    }

    public void twoDimFor() {
        int [][] twoDim = {{1, 2, 3}, {4, 5, 6}};
        int rowCounter = 0;
        for (int [] row:twoDim) {
            int colCounter = 0;
            for (int col:row) {
                System.out.print(col);
                colCounter++;
            }
            rowCounter++;
        }
    }
}