package c.inner;

public class AnonymousSample {
    public static void main(String []args) {
        AnonymousSample sample = new AnonymousSample();
        sample.setButtonListener();
        sample.setButtonListenerAnonymous();
        sample.setButtonListenerAnonymousObject();
    }

    public void setButtonListener() {
        MagicButton button = new MagicButton();
        MagicButtonListener listener = new MagicButtonListener();
        button.setListener(listener);
        button.onClickProcess();
    }

    public void setButtonListenerAnonymous() {
        MagicButton button = new MagicButton();
        button.setListener(new EventListener() {
            public void onClick() {
                System.out.println("Magic Button Clicked!!!");
            }
        });
        button.onClickProcess();
    }

    public void setButtonListenerAnonymousObject() {
        MagicButton button = new MagicButton();
        EventListener listener = new EventListener() {
            @Override
            public void onClick() {
                System.out.println("Magic Button Clicked!!!");
            }
        };
        button.setListener(listener);
        button.onClickProcess();
    }
    
    class MagicButtonListener implements EventListener {
        public void onClick() {
            System.out.println("Magic Button Clicked!!");
        }
    }
}