public class ArrayTwoDimension {
    public static void main(String [] args) {
        ArrayTwoDimension array = new ArrayTwoDimension();
        array.twoDimensionArray();
    }

    public void twoDimensionArray(){
        int [][] twoDim1;
        twoDim1 = new int[2][3];

        int [][] twoDim2;
        twoDim2 = new int[2][];
        twoDim2[0] = new int[3];
        twoDim2[1] = new int[2];

        int [][] twoDim3 = {{1, 2, 3}, {4, 5, 6}};
    }
}