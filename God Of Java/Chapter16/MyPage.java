
public class MyPage {
    InputBox input;

    public static void main(String []args) {
        MyPage myPage = new MyPage();
        myPage.setUI();
        myPage.pressKey();
    }

    public void setUI() {
        input = new InputBox();
        KeyEventListener listener = new KeyEventListener() {
            @Override
            public void onKeyDown() {
                System.out.println("Key Down");
            }

            @Override
            public void onKeyUp() {
                System.out.println("Key Up");
            }
        };

        input.setKeyEventListener(listener);
    }

    public void pressKey() {
        input.listenerCalled(input.KEY_DOWN);
        input.listenerCalled(input.KEY_UP);
    }
}