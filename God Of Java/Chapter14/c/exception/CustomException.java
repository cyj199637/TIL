package c.exception;

public class CustomException {
    public static void main(String []args) {
        CustomException sample = new CustomException();
        try {
            sample.thworMyException(13);
        } catch (MyException mye) {
            mye.printStackTrace();
        } 
    }

    public void thworMyException(int number) throws MyException {
        try {
            if (number > 12) {
                throw new MyException("Number is over than 12");
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}