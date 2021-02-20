package lambda;

public class CalculateSample {
    public static void main(String[] args) {
        CalculateSample sample = new CalculateSample();
        sample.calculateClassic();
        sample.calculateLambda();
    }

    private void calculateClassic() {
        Calculate calculateAdd = new Calculate(){
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };
        System.out.println(calculateAdd.operation(1, 2));
    }

    private void calculateLambda() {
        Calculate calculateAdd = (a, b) -> a + b;
        System.out.println(calculateAdd.operation(3, 4));
        
        Calculate calculateSubstract = (a, b) -> a - b;
        System.out.println(calculateSubstract.operation(2, 1));
    }
}