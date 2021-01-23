public class ArrayLength {
    public static void main(String [] args) {
        ArrayLength array = new ArrayLength();
        // array.printArrayLength();
        array.printArray();
    }

    public void printArrayLength(){
        int [] oneDim = new int[3];
        int [][] twoDim = new int[4][2];
        System.out.println(oneDim.length);
        System.out.println(twoDim.length);
        System.out.println(twoDim[0].length);
    }

    public void printArray(){
        int [][] twoDim = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("twoDim.length="+twoDim.length);
        System.out.println("twoDim[0].length="+twoDim[0].length);

        int rowLength = twoDim.length;
        for (int row=0; row<rowLength; row++) {
            int colLength = twoDim[row].length;
            for (int col=0; col<colLength; col++) {
                System.out.print(twoDim[row][col]+" ");
            }
            System.out.println();
        }
    }
}