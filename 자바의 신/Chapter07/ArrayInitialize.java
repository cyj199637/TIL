public class ArrayInitialize {
    String [] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                        
    public static void main(String [] args) {
        ArrayInitialize array = new ArrayInitialize();
        // array.otherInit();
        System.out.println(array.getMonth(3));
    }

    public void otherInit() {
        int [] lottoNumbers = {5, 12, 23, 25, 38, 41, 2};
    }

    public String getMonth(int monthInt) {
        return months[monthInt - 1];
    }
}