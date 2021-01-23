public class MethodVarArgs {
    public static void main(String [] args) {
        MethodVarArgs varArgs = new MethodVarArgs();
        varArgs.calculateNumbersWithArray(new int []{1, 2, 3, 4, 5});
        varArgs.calculateNumbers(1, 2, 3, 4, 5);
    }

    public void calculateNumbersWithArray(int []numbers){}
    public void calculateNumbers(int...numbers){
        int total = 0;
        for(int number:numbers) {
            total += number;
        }
        System.out.println("Total="+total);
    }
}